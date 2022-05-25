package ru.marslab.simplepressure.ui.mainscreen

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import ru.marslab.simplepressure.core.BaseViewModel
import ru.marslab.simplepressure.domain.interactor.AddNewDataInteractor
import ru.marslab.simplepressure.domain.interactor.GetAllDataInteractor
import ru.marslab.simplepressure.ui.mainscreen.model.MainAction
import ru.marslab.simplepressure.ui.mainscreen.model.MainEvent
import ru.marslab.simplepressure.ui.mainscreen.model.MainState
import ru.marslab.simplepressure.ui.mainscreen.model.toDomain
import ru.marslab.simplepressure.ui.mainscreen.model.toUi
import ru.marslab.simplepressure.ui.mainscreen.widget.PressureDataBottomSheetWidgetModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllData: GetAllDataInteractor,
    private val addNewData: AddNewDataInteractor
) :
    BaseViewModel<MainState, MainEvent, MainAction>(MainState()) {
    val pressureDataBottomSheetWidgetModel = PressureDataBottomSheetWidgetModel()

    init {
        collectWidgetsActions(pressureDataBottomSheetWidgetModel)
        launch {
            getAllData()
                .catch { handleError(it) }
                .map { data ->
                    data.map { it.toUi() }
                }
                .collect {
                    Log.d("TAG", "$it: ")
                    reduceState { state.value.copy(data = it) }
                }
        }
    }

    override fun reduceStateByAction(currentState: MainState, action: MainAction): MainState =
        when (action) {
            MainAction.FabClick -> currentState.copy(isShowPressureDialog = true)
            is MainAction.AddDataClick -> {
                launch {
                    addNewData(action.toDomain())
                }
                currentState.copy(isShowPressureDialog = false)
            }
            is MainAction.SetDialogSheetVisible -> currentState.copy(isShowPressureDialog = action.state)
        }
}
