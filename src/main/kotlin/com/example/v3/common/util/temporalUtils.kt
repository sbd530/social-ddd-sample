package com.example.v3.common.util

import com.example.v3.common.Timestamp
import com.example.v3.common.toTimestamp
import java.time.Instant
import kotlin.time.Duration.Companion.milliseconds

fun currentTimestamp(): Timestamp =
    Instant.now().toEpochMilli().milliseconds.toTimestamp()
