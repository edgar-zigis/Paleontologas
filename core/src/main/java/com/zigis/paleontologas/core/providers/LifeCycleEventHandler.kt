package com.zigis.paleontologas.core.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
fun LifecycleEventHandler(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: (event: Lifecycle.Event) -> Unit
) {
    val eventHandler = rememberUpdatedState(onEvent)

    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            eventHandler.value(event)
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}