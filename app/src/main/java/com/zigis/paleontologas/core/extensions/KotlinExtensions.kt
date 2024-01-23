package com.zigis.paleontologas.core.extensions

import kotlinx.coroutines.channels.SendChannel

fun <T> SendChannel<T>.sendSafely(element: T) {
    try {
        trySend(element)
    } catch (ignored: Throwable) { }
}