package com.zigis.paleontologas.features.main.stories.language

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.main.stories.language.list.LanguageListItem
import java.util.Locale

data class LanguageViewState(
    val currentLocale: Locale? = null,
    val localeItems: List<LanguageListItem> = emptyList()
) : IState