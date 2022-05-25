package ru.marslab.simplepressure.ui.mainscreen.model

data class PressureUi(
    val date: String,
    val time: String,
    val sistolPressure: Int,
    val diastolPressure: Int,
    val pulse: Int
)
