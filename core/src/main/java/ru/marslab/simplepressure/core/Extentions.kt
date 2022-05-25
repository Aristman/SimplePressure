package ru.marslab.simplepressure.core

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

@OptIn(FlowPreview::class)
fun <T, K> Flow<T>.groupBy(getKey: (T) -> K): Flow<Pair<K, List<T>>> = flow {
    map {
        val storage = mutableMapOf<K, MutableList<T>>()
        storage.getOrPut(getKey(it)) { mutableListOf() } += it
        storage.forEach { (k, ts) -> emit(k to ts) }
    }
}
