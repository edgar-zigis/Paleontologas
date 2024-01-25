package com.zigis.paleontologas.features.launcher.stories.main

import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent

sealed class LauncherIntent : IIntent {
    data object Initialize: LauncherIntent()
}