package com.zigis.paleontologas.features.launcher.stories.main

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState

data class LauncherViewState(
    val errorMessage: String? = null
) : IState