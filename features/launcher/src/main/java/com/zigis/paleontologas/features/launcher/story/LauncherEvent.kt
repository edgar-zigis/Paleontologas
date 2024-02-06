package com.zigis.paleontologas.features.launcher.story

import com.zigis.paleontologas.core.architecture.interfaces.IEvent

sealed class LauncherEvent : IEvent {
    data class ShowError(val message: String?) : LauncherEvent()
}