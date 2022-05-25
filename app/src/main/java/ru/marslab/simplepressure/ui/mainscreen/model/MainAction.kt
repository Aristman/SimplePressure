package ru.marslab.simplepressure.ui.mainscreen.model

import ru.marslab.simplepressure.core.Action

sealed class MainAction : Action {
    object FabClick : MainAction()
    data class AddDataClick(
        val sistolPressure: Int,
        val diastolPressure: Int,
        val pulse: Int
    ) : MainAction()

    data class SetDialogSheetVisible(val state: Boolean) : MainAction()
}
