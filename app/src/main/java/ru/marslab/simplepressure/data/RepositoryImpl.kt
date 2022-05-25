package ru.marslab.simplepressure.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import ru.marslab.simplepressure.domain.Repository
import ru.marslab.simplepressure.domain.model.Pressure
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {

    override val dispatcher: CoroutineDispatcher = Dispatchers.Default

    private val data = mutableListOf<Pressure>()

    override suspend fun saveNewData(pressure: Pressure) {
        data.add(pressure)
    }

    override fun getAllRecords(): Flow<List<Pressure>> {
        TODO()
    }
}
