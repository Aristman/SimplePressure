package ru.marslab.simplepressure.ui.mainscreen.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.marslab.simplepressure.R
import ru.marslab.simplepressure.core.BaseWidgetModel
import ru.marslab.simplepressure.ui.mainscreen.model.MainAction

data class PressureDataBottomSheetState(
    val sistolPressure: Int = 120,
    val diastolPressure: Int = 80,
    val pulse: Int = 60
)

class PressureDataBottomSheetWidgetModel :
    BaseWidgetModel<PressureDataBottomSheetState>(PressureDataBottomSheetState()) {
    fun setPressureData(
        sistolPressure: Int? = null,
        diastolPressure: Int? = null,
        pulse: Int? = null
    ) {
        setState {
            state.value.copy(
                sistolPressure = sistolPressure ?: state.value.sistolPressure,
                diastolPressure = diastolPressure ?: state.value.diastolPressure,
                pulse = pulse ?: state.value.pulse
            )
        }
    }

    fun clickAddData() {
        this sendAction MainAction.AddDataClick(
            sistolPressure = state.value.sistolPressure,
            diastolPressure = state.value.diastolPressure,
            pulse = state.value.pulse
        )
    }
}

@Composable
fun PressureDataBottomSheet(
    widgetModel: PressureDataBottomSheetWidgetModel
) {
    val widState = widgetModel.state.collectAsState()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(64.dp))
        PressureBlock(widState, widgetModel)
        Spacer(modifier = Modifier.height(16.dp))
        PulseBlock(widState, widgetModel)
        Button(onClick = widgetModel::clickAddData) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Composable
private fun PulseBlock(
    widState: State<PressureDataBottomSheetState>,
    widgetModel: PressureDataBottomSheetWidgetModel
) {
    DigitalInputField(value = widState.value.pulse.toString()) {
        widgetModel.setPressureData(pulse = it)
    }
    Text(
        text = stringResource(id = R.string.pulse),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun PressureBlock(
    widState: State<PressureDataBottomSheetState>,
    widgetModel: PressureDataBottomSheetWidgetModel
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        DigitalInputField(widState.value.sistolPressure.toString()) {
            widgetModel.setPressureData(sistolPressure = it)
        }
        Text(
            text = stringResource(id = R.string.pressure_beetwen_char),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        DigitalInputField(widState.value.diastolPressure.toString()) {
            widgetModel.setPressureData(diastolPressure = it)
        }
    }
    Text(
        text = stringResource(id = R.string.pressure),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun DigitalInputField(
    value: String,
    changeValue: (value: Int?) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { changeValue(it.toIntOrNull()) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.width(96.dp)
    )
}
