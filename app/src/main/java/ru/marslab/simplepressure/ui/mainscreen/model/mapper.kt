package ru.marslab.simplepressure.ui.mainscreen.model

import ru.marslab.simplepressure.domain.model.Pressure
import ru.marslab.simplepressure.extractDate
import ru.marslab.simplepressure.extractTime
import java.time.LocalDateTime

fun Pressure.toUi(): PressureUi =
    PressureUi(
        date = dateTime.extractDate(),
        time = dateTime.extractTime(),
        sistolPressure = sistolPressure,
        diastolPressure = diastolPressure,
        pulse = pulse
    )

fun MainAction.AddDataClick.toDomain(): Pressure =
    Pressure(
        dateTime = LocalDateTime.now().toString(),
        sistolPressure = sistolPressure,
        diastolPressure = diastolPressure,
        pulse = pulse
    )
