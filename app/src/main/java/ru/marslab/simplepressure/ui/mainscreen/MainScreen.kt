package ru.marslab.simplepressure.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.marslab.simplepressure.R
import ru.marslab.simplepressure.ui.mainscreen.model.MainAction
import ru.marslab.simplepressure.ui.mainscreen.model.PressureUi
import ru.marslab.simplepressure.ui.mainscreen.widget.PressureDataBottomSheet

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreenView(viewModel: MainViewModel = viewModel()) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val state = viewModel.state.collectAsState()
    BottomSheetScaffold(
        floatingActionButton = { MainFAB { viewModel sendAction MainAction.FabClick } },
        sheetContent = { PressureDataBottomSheet(widgetModel = viewModel.pressureDataBottomSheetWidgetModel) },
        scaffoldState = scaffoldState
    ) {
        LazyColumn(contentPadding = it) {
            val mapData = state.value.data
            mapData.forEach { dayData ->
                item {
                    PressureListHeader(dayData.key)
                }
                items(items = dayData.value) { pressureData ->
                    PressureListItem(item = pressureData)
                }
            }
        }
    }
    LaunchedEffect(key1 = scaffoldState.bottomSheetState.currentValue) {
        viewModel sendAction MainAction.SetDialogSheetVisible(scaffoldState.bottomSheetState.isExpanded)
    }
    LaunchedEffect(key1 = state.value.isShowPressureDialog) {
        if (state.value.isShowPressureDialog) {
            scaffoldState.bottomSheetState.expand()
        } else {
            scaffoldState.bottomSheetState.collapse()
        }
    }
}

@Composable
private fun PressureListHeader(title: String) {
    Divider(thickness = 3.dp, color = Color.DarkGray)
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    0.0f to Color.Gray,
                    0.5f to Color.LightGray,
                    1.0f to Color.Gray
                )
            )
            .padding(8.dp)
    )
}

@Composable
private fun PressureListItem(item: PressureUi) {
    Divider(color = Color.DarkGray)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    0.0f to Color.White,
                    0.5f to when {
                        item.sistolPressure > 160 -> Color.Red
                        item.sistolPressure > 140 -> Color.Yellow
                        else -> Color.Green
                    },
                    1.0f to Color.White
                )
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.time)
        Text(text = item.sistolPressure.toString())
        Text(text = stringResource(id = R.string.pressure_beetwen_char))
        Text(text = item.diastolPressure.toString())
        Icon(painter = painterResource(id = R.drawable.heart), contentDescription = null)
        Text(text = item.pulse.toString())
    }
}

@Composable
private fun MainFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Image(painter = painterResource(id = R.drawable.heart), contentDescription = null)
    }
}
