package com.zigis.paleontologas.features.main.stories.about

import com.zigis.paleontologas.core.architecture.v2.BaseViewModel
import com.zigis.paleontologas.core.managers.ApplicationVersionManager

class AboutViewModel(
    private val applicationVersionManager: ApplicationVersionManager
) : BaseViewModel<AboutViewState, AboutIntent>() {

    override fun getInitialData() = AboutViewState()

    override suspend fun handleIntent(intent: AboutIntent) {
        when (intent) {
            is AboutIntent.Initialize -> initialize()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                applicationVersion = applicationVersionManager.getApplicationVersionName()
            )
        }
    }
}