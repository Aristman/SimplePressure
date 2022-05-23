package ru.marslab.simplepressure.core

import kotlinx.coroutines.CoroutineDispatcher

interface BaseRepository {
    val dispatcher: CoroutineDispatcher
}
