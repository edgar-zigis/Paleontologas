package com.zigis.paleontologas.features.launcher.story

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class LauncherIntent : IIntent {
    data object Initialize : LauncherIntent()
}