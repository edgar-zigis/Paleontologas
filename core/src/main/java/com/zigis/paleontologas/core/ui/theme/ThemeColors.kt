package com.zigis.paleontologas.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class ThemeColors(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val contentBackground: Color,
    val contentText: Color,
    val headingText: Color
) {
    companion object {

        val Success = Color(0xFF009688)
        val Failure = Color(0xFFD32F2F)

        val LightThemeColors = ThemeColors(
            backgroundPrimary = Color(0xFF537895),
            backgroundSecondary = Color(0xFF09203F),
            contentBackground = Color(0xFFFFFFFF),
            contentText = Color(0xFF09203F),
            headingText = Color(0xFF3E5771)
        )

        val DarkThemeColors = ThemeColors(
            backgroundPrimary = Color(0xFF537895),
            backgroundSecondary = Color(0xFF09203F),
            contentBackground = Color(0xFFFFFFFF),
            contentText = Color(0xFF09203F),
            headingText = Color(0xFF3E5771)
        )
    }
}

internal val LocalThemeColors = staticCompositionLocalOf { ThemeColors.LightThemeColors }
