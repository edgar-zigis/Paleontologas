package com.zigis.paleontologas.features.settings.stories.settings

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import com.zigis.paleontologas.features.settings.routing.SettingsRouter
import com.zigis.paleontologas.features.settings.stories.settings.SettingsScreenIntent.*

class SettingsViewModel(
    private val settingsRouter: SettingsRouter,
    private val applicationPreferences: ApplicationPreferences
) : BaseViewModel<SettingsScreenState, SettingsScreenIntent>() {

    override fun getInitialData() = SettingsScreenState()

    override suspend fun handleIntent(intent: SettingsScreenIntent) {
        when (intent) {
            is Initialize -> initialize()
            is ToggleVibration -> toggleVibration(isEnabled = intent.isEnabled)
            is OpenLanguages -> settingsRouter.openLanguages()
            is OpenAbout -> settingsRouter.openAboutSection()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                isVibrationEnabled = applicationPreferences.isVibrationEnabled
            )
        }
    }

    private suspend fun toggleVibration(isEnabled: Boolean) {
        applicationPreferences.isVibrationEnabled = isEnabled
        updateState {
            it.copy(
                isVibrationEnabled = isEnabled
            )
        }
    }
}