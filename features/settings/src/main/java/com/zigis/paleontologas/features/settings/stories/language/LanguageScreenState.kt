package com.zigis.paleontologas.features.settings.stories.language

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.settings.stories.language.list.LanguageListItem
import java.util.Locale

data class LanguageScreenState(
    val currentLocale: Locale? = null,
    val localeItems: List<LanguageListItem> = emptyList()
) : IState