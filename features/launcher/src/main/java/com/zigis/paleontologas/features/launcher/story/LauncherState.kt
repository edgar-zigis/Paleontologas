package com.zigis.paleontologas.features.launcher.story

import com.zigis.paleontologas.core.architecture.interfaces.IState

data class LauncherState(
    val errorMessage: String? = null
) : IState