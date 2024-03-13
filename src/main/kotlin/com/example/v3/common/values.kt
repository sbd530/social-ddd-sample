package com.example.v3.common

import java.net.URL
import java.nio.file.Path
import kotlin.time.Duration

@JvmInline
value class Timestamp(val duration: Duration)

fun Duration.toTimestamp() = Timestamp(duration = this)

data class Image(
    val url: ImageFileLocation,
    val size: ImageSize? = null,
    val width: Int? = null,
    val height: Int? = null
)

sealed class ImageFileLocation {
    data class LocalFileSystem(val path: Path)
    data class RemoteFileStorage(val url: URL)
}

@JvmInline
value class ImageSize(val bytes: Long)

@JvmInline
value class Nickname(val value: String)
