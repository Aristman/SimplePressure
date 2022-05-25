package ru.marslab.simplepressure.ui.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.marslab.simplepressure.R
import ru.marslab.simplepressure.ui.mainscreen.model.MainAction
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
private fun MainFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Image(painter = painterResource(id = R.drawable.heart), contentDescription = null)
    }
}
