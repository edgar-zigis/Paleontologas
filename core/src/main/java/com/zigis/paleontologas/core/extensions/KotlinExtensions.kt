package com.zigis.paleontologas.core.extensions

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.channels.SendChannel
import kotlin.coroutines.resume

fun <T> SendChannel<T>.sendSafely(element: T) {
    try {
        trySend(element)
    } catch (ignored: Throwable) {
    }
}

fun <T> CancellableContinuation<T>.resumeSafely(e: T) {
    if (this.isActive) {
        resume(e)
    }
}