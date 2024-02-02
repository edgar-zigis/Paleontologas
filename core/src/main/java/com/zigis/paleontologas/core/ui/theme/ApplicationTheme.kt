package com.zigis.paleontologas.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    val isSystemDarkTheme = isSystemInDarkTheme()
    val rememberColors = remember {
        if (isSystemDarkTheme) ThemeColors.DarkThemeColors else ThemeColors.LightThemeColors
    }

    CompositionLocalProvider(
        ThemeColors.Local provides rememberColors,
        ThemeTypography.Local provides ThemeTypography.Typography
    ) {
        MaterialTheme {
            content()
        }
    }
}

object ApplicationTheme {
    val colors: ThemeColors
        @Composable
        @ReadOnlyComposable
        get() = ThemeColors.Local.current

    val typography: ThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = ThemeTypography.Local.current
}