package ru.marslab.simplepressure

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.extractDate(): String =
    runCatching {
        LocalDateTime
            .parse(this)
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("dd MMMM"))
            .toString()
    }.getOrNull().orEmpty()

fun String.extractTime(): String =
    runCatching {
        LocalDateTime
            .parse(this)
            .toLocalTime()
            .format(DateTimeFormatter.ofPattern("HH:mm"))
            .toString()
    }.getOrNull().orEmpty()
