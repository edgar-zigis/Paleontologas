package com.zigis.paleontologas.core.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class ThemeTypography(
    val headline1: TextStyle,
    val headline2: TextStyle,
    val headline3: TextStyle,
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
    val footNote1: TextStyle,
    val content: TextStyle
) {
    companion object {
        val Typography = ThemeTypography(
            headline1 = TextStyle(
                fontSize = 22.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Bold,
            ),
            headline2 = TextStyle(
                fontSize = 20.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Medium
            ),
            headline3 = TextStyle(
                fontSize = 19.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Bold
            ),
            title1 = TextStyle(
                fontSize = 19.sp,
                fontFamily = ThemeFonts.Gentona,
                fontWeight = FontWeight.Normal
            ),
            title2 = TextStyle(
                fontSize = 17.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Bold
            ),
            title3 = TextStyle(
                fontSize = 15.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.ExtraLight
            ),
            subtitle1 = TextStyle(
                fontSize = 17.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Light
            ),
            subtitle2 = TextStyle(
                fontSize = 14.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Normal
            ),
            caption1 = TextStyle(
                fontSize = 15.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Light
            ),
            caption2 = TextStyle(
                fontSize = 12.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Light
            ),
            footNote1 = TextStyle(
                fontSize = 13.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Normal
            ),
            content = TextStyle(
                fontSize = 17.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Medium,
                lineHeight = 20.sp
            )
        )

        val Local = staticCompositionLocalOf { Typography }
    }
}