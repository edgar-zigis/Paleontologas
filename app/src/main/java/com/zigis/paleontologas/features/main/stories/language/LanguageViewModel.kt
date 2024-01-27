package com.zigis.paleontologas.features.main.stories.language

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.features.main.routers.MainRouter
import com.zigis.paleontologas.features.main.stories.language.LanguageIntent.*
import java.util.Locale

class LanguageViewModel(
    private val mainRouter: MainRouter,
    private val applicationLocaleManager: ApplicationLocaleManager
) : BaseViewModel<LanguageViewState, LanguageIntent>() {

    override fun getInitialData() = LanguageViewState()

    override suspend fun handleIntent(intent: LanguageIntent) {
        when (intent) {
            is Initialize -> initialize()
            is ChangeLocale -> changeLocale(locale = intent.locale)
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                currentLocale = applicationLocaleManager.getCurrentLocale(),
                localeList = applicationLocaleManager.getAvailableLocales()
            )
        }
    }

    private fun changeLocale(locale: Locale) {
        applicationLocaleManager.setCurrentLocale(locale)
        mainRouter.openMainScreen()
    }
}