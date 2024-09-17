package com.zigis.paleontologas.features.settings.factories

import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.features.settings.stories.language.list.LanguageListItem

class LanguageListItemFactory(
    private val applicationLocaleManager: ApplicationLocaleManager
) {
    fun getItems(): List<LanguageListItem> {
        return applicationLocaleManager.getAvailableLocales().map {
            LanguageListItem(locale = it)
        }
    }
}