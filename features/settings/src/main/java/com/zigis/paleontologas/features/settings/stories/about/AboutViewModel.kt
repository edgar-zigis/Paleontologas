package com.zigis.paleontologas.features.settings.stories.about

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.features.settings.factories.AboutListItemFactory
import com.zigis.paleontologas.features.settings.routing.SettingsRouter
import com.zigis.paleontologas.features.settings.stories.about.AboutScreenIntent.*

class AboutViewModel(
    private val settingsRouter: SettingsRouter,
    private val aboutListItemFactory: AboutListItemFactory,
    private val applicationVersionManager: ApplicationVersionManager
) : BaseViewModel<AboutViewState, AboutScreenIntent>() {

    override fun getInitialData() = AboutViewState()

    override suspend fun handleIntent(intent: AboutScreenIntent) {
        when (intent) {
            is Initialize -> initialize()
            is InvokeBack -> settingsRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                contributorItems = aboutListItemFactory.getItems(),
                applicationVersion = applicationVersionManager.getApplicationVersionName()
            )
        }
    }
}