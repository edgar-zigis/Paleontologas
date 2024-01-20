package com.zigis.paleontologas.features.main.stories.language

import java.util.Locale

interface LanguageViewDelegate {
    fun onLocaleSelected(locale: Locale)
    fun onBackInvoked()
}