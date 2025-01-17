package com.example.weatherhub.core.mics

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

fun <T> emitFlow(emitter: suspend () -> T): Flow<T> {
    return emitter.asFlow()
}

@OptIn(ExperimentalCoroutinesApi::class)
fun <T, R> Flow<T>.flatMapSame(stream: suspend (T) -> Flow<R>): Flow<T> =
    flatMapConcat { original -> stream(original).map { original } }