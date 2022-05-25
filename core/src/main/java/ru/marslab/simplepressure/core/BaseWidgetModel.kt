package ru.marslab.simplepressure.core

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseWidgetModel<D>(initValue: D) {
    private val _state = MutableStateFlow(initValue)
    val state: StateFlow<D> = _state.asStateFlow()

    private val _action = MutableSharedFlow<Action>(extraBufferCapacity = 1)
    val action: SharedFlow<Action>
        get() = _action.asSharedFlow()

    infix fun sendAction(action: Action) {
        _action.tryEmit(action)
    }

    fun setState(state: () -> D) {
        _state.value = state()
    }
}
