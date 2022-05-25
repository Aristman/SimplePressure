package ru.marslab.simplepressure.domain.interactor

import ru.marslab.simplepressure.domain.Repository
import ru.marslab.simplepressure.domain.model.Pressure
import javax.inject.Inject

class AddNewDataInteractor @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(pressure: Pressure) {
        repository.saveNewData(pressure)
    }
}
