package com.zigis.paleontologas.launcher.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.application.entities.TaskStatus
import com.zigis.paleontologas.launcher.managers.DataMigrationManager
import kotlinx.coroutines.*

class LauncherViewModel constructor(
    private val dataMigrationManager: DataMigrationManager
) : ViewModel() {

    val synchronizationStatus = MutableLiveData<TaskStatus>()

    private val synchronizeDataExceptionHandler = CoroutineExceptionHandler { _, error ->
        viewModelScope.launch {
            synchronizationStatus.value = TaskStatus.failure(error.localizedMessage)
        }
    }

    fun synchronizeData() = GlobalScope.launch(Dispatchers.IO + synchronizeDataExceptionHandler) {
        dataMigrationManager.migrate()
        withContext(Dispatchers.Main) {
            delay(1800)
            synchronizationStatus.value = TaskStatus.success()
        }
    }
}