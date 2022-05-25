package ru.marslab.simplepressure.ui.mainscreen.model

data class MainState(
    val isShowPressureDialog: Boolean = false,
    val data: List<PressureUi> = emptyList()
)
