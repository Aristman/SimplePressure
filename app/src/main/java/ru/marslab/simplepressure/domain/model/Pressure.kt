package ru.marslab.simplepressure.domain.model

data class Pressure(
    val dateTime: String,
    val sistolPressure: Int,
    val diastolPressure: Int,
    val pulse: Int
)
