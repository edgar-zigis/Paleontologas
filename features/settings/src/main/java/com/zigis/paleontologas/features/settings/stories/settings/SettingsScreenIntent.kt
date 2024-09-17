package com.zigis.paleontologas.features.settings.stories.settings

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class SettingsScreenIntent : IIntent {
    data object Initialize : SettingsScreenIntent()
    data object OpenLanguages : SettingsScreenIntent()
    data object OpenAbout : SettingsScreenIntent()
    data class ToggleVibration(val isEnabled: Boolean) : SettingsScreenIntent()
}