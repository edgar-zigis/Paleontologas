package com.zigis.paleontologas.features.launcher.story

import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.architecture.BaseViewModelV2
import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.features.launcher.story.LauncherIntent.*
import com.zigis.paleontologas.features.main.routers.MainRouter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(DelicateCoroutinesApi::class)
class LauncherViewModel(
    private val mainRouter: MainRouter,
    private val dataMigrationManager: DataMigrationManager
) : BaseViewModelV2<LauncherState, LauncherIntent, LauncherEvent>() {

    override val initialState = LauncherState()
    override val state: StateFlow<LauncherState>
        get() = MutableStateFlow(LauncherState())

    override suspend fun handleIntent(intent: LauncherIntent) {
        when (intent) {
            is Initialize -> initialize()
        }
    }

    private fun initialize() = GlobalScope.launch(Dispatchers.IO) {
        try {
            dataMigrationManager.migrate()
            withContext(Dispatchers.Main) {
                delay(1800)
                mainRouter.openMainScreen()
            }
        } catch (error: Throwable) {
            viewModelScope.launch {
                sendEvent(LauncherEvent.ShowError(message = error.localizedMessage))
            }
        }
    }
}