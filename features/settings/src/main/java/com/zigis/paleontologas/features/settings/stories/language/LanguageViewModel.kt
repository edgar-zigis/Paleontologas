package com.zigis.paleontologas.features.settings.stories.language

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.features.settings.factories.LanguageListItemFactory
import com.zigis.paleontologas.features.settings.routing.SettingsRouter
import com.zigis.paleontologas.features.settings.stories.language.LanguageScreenIntent.*
import java.util.Locale

class LanguageViewModel(
    private val settingsRouter: SettingsRouter,
    private val languageListItemFactory: LanguageListItemFactory,
    private val applicationLocaleManager: ApplicationLocaleManager
) : BaseViewModel<LanguageScreenState, LanguageScreenIntent>() {

    override fun getInitialData() = LanguageScreenState()

    override suspend fun handleIntent(intent: LanguageScreenIntent) {
        when (intent) {
            is Initialize -> initialize()
            is ChangeLocale -> changeLocale(locale = intent.locale)
            is InvokeBack -> settingsRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                currentLocale = applicationLocaleManager.getCurrentLocale(),
                localeItems = languageListItemFactory.getItems()
            )
        }
    }

    private fun changeLocale(locale: Locale) {
        applicationLocaleManager.setCurrentLocale(locale)
        //  TODO: mainRouter.openMainScreen()
    }
}