package com.example.v3

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource
import java.nio.file.Files

class RayCastingTest {
    @Test
    fun test() {
        // Example 1
        val cityPolygon = listOf(
            Pair(37.0, -122.0),
            Pair(37.0, -123.0),
            Pair(38.0, -123.0),
            Pair(38.0, -122.0)
        )
        val testPoint = Pair(37.5, -122.5) // 테스트하고자 하는 점의 위도, 경도
        assertThat(testPoint.isInside(cityPolygon)).isTrue()

        // Example 2
        val concavePolygon = listOf(
            Pair(48.8566, 2.3522),
            Pair(48.8570, 2.3530),
            Pair(48.8575, 2.3540),
            Pair(48.8580, 2.3550),
            Pair(48.8585, 2.3560),
            Pair(48.8590, 2.3550), // 오목한 부분 시작
            Pair(48.8585, 2.3540), // 오목한 부분
            Pair(48.8580, 2.3530), // 오목한 부분 끝
            Pair(48.8575, 2.3520),
            Pair(48.8570, 2.3510)
        )
        // Example 2-1
        val insideCoordinates = arrayOf(
            Pair(48.8578, 2.3535), // 다각형 중앙에 가까운 좌표
            Pair(48.8573, 2.3525), // 다각형 중앙으로부터 약간 벗어난 좌표
            Pair(48.8583, 2.3545), // 다각형 중앙 쪽으로부터 오목한 부분 근처
            Pair(48.8575, 2.3532)  // 다각형의 오목한 부분 근처 내부 좌표
        )
        assertThat(insideCoordinates).allMatch { it.isInside(concavePolygon)
        }
        val concavePolygon1 = arrayOf(
            Pair(48.8566, 2.3522),
            Pair(48.8570, 2.3530),
            Pair(48.8575, 2.3540),
            Pair(48.8580, 2.3550),
            Pair(48.8585, 2.3560),
            Pair(48.8590, 2.3550), // 오목한 부분 시작
            Pair(48.8585, 2.3540), // 오목한 부분
            Pair(48.8580, 2.3530), // 오목한 부분 끝
            Pair(48.8575, 2.3520),
            Pair(48.8570, 2.3510)
        )
        // Example 2-2
        val outsideCoordinates = arrayOf(
            Pair(48.8560, 2.3510),
            Pair(48.8595, 2.3570),
            Pair(48.8582, 2.3528)
        )
        assertThat(outsideCoordinates).allMatch { it.isOutside(concavePolygon) }
    }

    @Test
    fun test2() {
        val resource = ClassPathResource("italy.json")
        val mapper: ObjectMapper = jacksonObjectMapper()
        val jsonString = String(Files.readAllBytes(resource.file.toPath()))

        val italyPolygon = mapper.readValue(jsonString, CoordinatesJson::class.java).coordinates
            .map { it[0] to it[1] }

        val insideCoordinates = listOf(
            10.426902713012652 to 44.34338305017238,
            13.641494402023397 to 41.62530635663978,
            18.062315477380366 to 40.25099870716113,
            14.065580993445877 to 37.66656161425843
        )
        insideCoordinates.forEach {
            assertThat(it.isInside(italyPolygon))
        }
        val outsideCoordinates = listOf(
            14.201025629127315 to 42.64508132524108,
            19.28116537488671 to 43.774261570933874,
            14.807272766887195 to 38.651082765093406,
            12.67776368884688 to 39.988526939659295
        )
        outsideCoordinates.forEach {
            assertThat(it.isOutside(italyPolygon))
        }
    }
}

fun Pair<Double, Double>.isOutside(polygon: List<Pair<Double, Double>>): Boolean = !isInside(polygon)

fun Pair<Double, Double>.isInside(polygon: List<Pair<Double, Double>>): Boolean {
    var crossings = 0
    val (px, py) = this

    for (i in polygon.indices) {
        val (x1, y1) = polygon[i]
        val (x2, y2) = polygon[(i + 1) % polygon.size]

        if (((y1 <= py && py < y2) || (y2 <= py && py < y1)) &&
            (px < (x2 - x1) * (py - y1) / (y2 - y1) + x1)) {
            crossings++
        }
    }

    return crossings % 2 != 0
}

data class CoordinatesJson(
    val coordinates: List<List<Double>>
)