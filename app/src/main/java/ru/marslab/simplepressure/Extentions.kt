package ru.marslab.simplepressure

import java.time.LocalDateTime

fun String.extractDate(): String =
    runCatching { LocalDateTime.parse(this).toLocalDate().toString() }.getOrNull().orEmpty()

fun String.extractTime(): String =
    runCatching { LocalDateTime.parse(this).toLocalTime().toString() }.getOrNull().orEmpty()
