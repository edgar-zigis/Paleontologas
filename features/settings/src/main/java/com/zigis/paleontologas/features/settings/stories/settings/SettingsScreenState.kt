package com.zigis.paleontologas.features.settings.stories.settings

import com.zigis.paleontologas.core.architecture.interfaces.IState

data class SettingsScreenState(
    val isVibrationEnabled: Boolean = true
) : IState