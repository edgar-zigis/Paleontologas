package com.zigis.paleontologas.core.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.zigis.paleontologas.core.R

object ThemeFonts {

    val Gentona = FontFamily(
        Font(R.font.gentona_book),
        Font(R.font.gentona_book, weight = FontWeight.Normal, style = FontStyle.Normal),
        Font(R.font.gentona_extra_light_italic, weight = FontWeight.ExtraLight, style = FontStyle.Italic)
    )

    val Gilroy = FontFamily(
        Font(R.font.gilroy_regular),
        Font(R.font.gilroy_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
        Font(R.font.gilroy_light, weight = FontWeight.Light, style = FontStyle.Normal),
        Font(R.font.gilroy_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
        Font(R.font.gilroy_bold, weight = FontWeight.Bold, style = FontStyle.Normal)
    )
}