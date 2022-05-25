package ru.marslab.simplepressure.data

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.marslab.simplepressure.core.exception.ServerException
import ru.marslab.simplepressure.domain.Repository
import ru.marslab.simplepressure.domain.model.Pressure
import javax.inject.Inject

private const val DATETIME_NAME = "dateTime"
private const val DIASTOL_PRESSURE_NAME = "diastolPressure"
private const val SISTOL_PRESSURE_NAME = "sistolPressure"
private const val PULSE_NAME = "pulse"
private const val COLLECTION_NAME = "pressureData"

class RepositoryFirestore @Inject constructor() : Repository {

    override val dispatcher: CoroutineDispatcher = Dispatchers.Default
    private val db = Firebase.firestore
    private val dataFlow: MutableStateFlow<List<Pressure>> = MutableStateFlow(emptyList())

    init {
        db.collection(COLLECTION_NAME)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    throw ServerException()
                } else {
                    dataFlow.value = value?.documents?.map {
                        Pressure(
                            dateTime = it[DATETIME_NAME, String::class.java].orEmpty(),
                            sistolPressure = it[SISTOL_PRESSURE_NAME, Int::class.java] ?: 0,
                            diastolPressure = it[DIASTOL_PRESSURE_NAME, Int::class.java] ?: 0,
                            pulse = it[PULSE_NAME, Int::class.java] ?: 0
                        )
                    }.orEmpty()
                }
            }
    }

    override suspend fun saveNewData(pressure: Pressure) {
        val newData = hashMapOf(
            DATETIME_NAME to pressure.dateTime,
            SISTOL_PRESSURE_NAME to pressure.sistolPressure,
            DIASTOL_PRESSURE_NAME to pressure.diastolPressure,
            PULSE_NAME to pressure.pulse
        )
        db.collection(COLLECTION_NAME)
            .add(newData)
            .addOnFailureListener {
                throw ServerException()
            }
    }

    override fun getAllRecords(): Flow<List<Pressure>> = dataFlow.asStateFlow()
}
