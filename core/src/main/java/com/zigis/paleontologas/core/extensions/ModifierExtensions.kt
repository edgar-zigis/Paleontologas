package com.zigis.paleontologas.core.extensions

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout

fun Modifier.parallaxLayoutModifier(
    scrollState: ScrollState,
    rate: Int,
    isBlurEnabled: Boolean = false
): Modifier {
    return layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val height = if (rate > 0) scrollState.value / rate else scrollState.value
        layout(placeable.width, placeable.height) {
            placeable.place(0, height)
        }
    }.graphicsLayer {
        if (!isBlurEnabled) return@graphicsLayer
        if (scrollState.value > 0) {
            renderEffect = BlurEffect( 0f,  scrollState.value * 0.04f)
        }
    }
}

@Composable
fun Modifier.debouncedClickable(
    debounceInterval: Long = 600L,
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    return this.then(
        Modifier.clickable(enabled = enabled) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime > debounceInterval) {
                lastClickTime = currentTime
                onClick()
            }
        }
    )
}

@Composable
fun rememberDebouncedClick(
    debounceTimeMillis: Long = 600L
): (onClick: () -> Unit) -> Unit {
    var lastClickTime by remember { mutableLongStateOf(0L) }

    return { onClick ->
        val now = System.currentTimeMillis()
        if (now - lastClickTime > debounceTimeMillis) {
            lastClickTime = now
            onClick()
        }
    }
}

