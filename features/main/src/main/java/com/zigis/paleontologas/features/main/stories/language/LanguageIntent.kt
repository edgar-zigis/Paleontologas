package com.zigis.paleontologas.features.main.stories.language

import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import java.util.Locale

sealed class LanguageIntent : IIntent {
    data object Initialize : LanguageIntent()
    data class ChangeLocale(val locale: Locale) : LanguageIntent()
    data object InvokeBack : LanguageIntent()
}