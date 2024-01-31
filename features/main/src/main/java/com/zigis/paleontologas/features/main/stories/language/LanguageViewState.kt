package com.zigis.paleontologas.features.main.stories.language

import com.zigis.paleontologas.core.architecture.interfaces.IState
import java.util.Locale

data class LanguageViewState(
    val currentLocale: Locale? = null,
    val localeList: List<Locale> = emptyList()
) : IState