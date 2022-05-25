package ru.marslab.simplepressure.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplepressure.domain.Repository
import ru.marslab.simplepressure.domain.model.Pressure
import javax.inject.Inject

class GetAllDataInteractor @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke(): Flow<List<Pressure>> =
        repository.getAllRecords()
}
