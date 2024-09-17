package com.zigis.paleontologas.features.main.stories.about

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import com.zigis.paleontologas.features.main.factories.AboutListItemFactory
import com.zigis.paleontologas.features.main.routing.MainRouter
import com.zigis.paleontologas.features.main.stories.about.AboutIntent.*

class AboutViewModel(
    private val mainRouter: MainRouter,
    private val aboutListItemFactory: AboutListItemFactory,
    private val applicationVersionManager: ApplicationVersionManager
) : BaseViewModel<AboutViewState, AboutIntent>() {

    override fun getInitialData() = AboutViewState()

    override suspend fun handleIntent(intent: AboutIntent) {
        when (intent) {
            is Initialize -> initialize()
            is InvokeBack -> mainRouter.popCurrentScreen()
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