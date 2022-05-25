package ru.marslab.simplepressure.domain

import kotlinx.coroutines.flow.Flow
import ru.marslab.simplepressure.core.BaseRepository
import ru.marslab.simplepressure.domain.model.Pressure

interface Repository : BaseRepository {
    suspend fun saveNewData(pressure: Pressure)
    fun getAllRecords(): Flow<List<Pressure>>
}
