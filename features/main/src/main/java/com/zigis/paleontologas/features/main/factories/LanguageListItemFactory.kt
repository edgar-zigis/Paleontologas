package com.zigis.paleontologas.features.main.factories

import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.features.main.stories.language.list.LanguageListItem

class LanguageListItemFactory(
    private val applicationLocaleManager: ApplicationLocaleManager
) {
    fun getItems(): List<LanguageListItem> {
        return applicationLocaleManager.getAvailableLocales().map {
            LanguageListItem(locale = it)
        }
    }
}