package com.zigis.paleontologas.core.extensions

import androidx.compose.foundation.ScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout

fun Modifier.parallaxLayoutModifier(
    scrollState: ScrollState,
    rate: Int,
    isBlurEnabled: Boolean = true
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
            renderEffect = BlurEffect( scrollState.value * 0.02f,  scrollState.value * 0.02f)
        }
    }
}