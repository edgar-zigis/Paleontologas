package com.zigis.paleontologas.features.launcher.stories.main

import android.content.Intent
import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.features.launcher.managers.DataMigrationManager
import com.zigis.paleontologas.features.main.MainActivity
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
class LauncherViewModel(
    private val dataMigrationManager: DataMigrationManager,
    private val androidLifecycleProvider: AndroidLifecycleProvider
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
                androidLifecycleProvider.getActivity()?.startActivity(Intent(
                    androidLifecycleProvider.getActivity(),
                    MainActivity::class.java
                ))
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