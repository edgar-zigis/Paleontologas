package com.zigis.paleontologas.launcher.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zigis.paleontologas.application.entities.TaskStatus
import com.zigis.paleontologas.launcher.managers.DataMigrationManager
import kotlinx.coroutines.*

class LauncherViewModel constructor(
    private val dataMigrationManager: DataMigrationManager
) : ViewModel() {

    val synchronizationStatus = MutableLiveData<TaskStatus>()

    fun synchronizeData() = GlobalScope.launch(Dispatchers.IO) {
        try {
            dataMigrationManager.migrate()
            withContext(Dispatchers.Main) {
                delay(1800)
                synchronizationStatus.value = TaskStatus.success()
            }
        } catch (error: Throwable) {
            synchronizationStatus.value = TaskStatus.failure(error.localizedMessage)
        }
    }
}