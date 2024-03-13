package com.example.v3.common.util

import java.util.*

fun randomUUID(): UUID = UUID.randomUUID()
fun String.toUUID(): UUID = UUID.fromString(this)
