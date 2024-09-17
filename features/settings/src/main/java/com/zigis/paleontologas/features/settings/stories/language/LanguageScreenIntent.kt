package com.zigis.paleontologas.features.settings.stories.language

import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import java.util.Locale

sealed class LanguageScreenIntent : IIntent {
    data object Initialize : LanguageScreenIntent()
    data class ChangeLocale(val locale: Locale) : LanguageScreenIntent()
    data object InvokeBack : LanguageScreenIntent()
}