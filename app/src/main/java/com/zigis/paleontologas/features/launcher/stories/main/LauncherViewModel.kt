package com.zigis.paleontologas.features.launcher.stories.main

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.features.main.routers.MainRouter
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
class LauncherViewModel(
    private val mainRouter: MainRouter,
    private val dataMigrationManager: DataMigrationManager
) : BaseViewModel<LauncherViewState, LauncherIntent>() {

    override fun getInitialData() = LauncherViewState()

    override suspend fun handleIntent(intent: LauncherIntent) {
        when (intent) {
            is LauncherIntent.Initialize -> initialize()
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
            updateState {
                it.copy(
                    errorMessage = error.localizedMessage
                )
            }
        }
    }
}