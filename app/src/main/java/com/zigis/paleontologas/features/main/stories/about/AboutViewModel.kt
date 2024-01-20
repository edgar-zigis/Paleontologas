package com.zigis.paleontologas.features.main.stories.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.core.managers.ApplicationVersionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AboutViewModel constructor(
    private val applicationVersionManager: ApplicationVersionManager
) : ViewModel() {

    val applicationVersion = DistinctLiveData<String>()

    fun loadApplicationVersion() = viewModelScope.launch(Dispatchers.IO) {
        val version = applicationVersionManager.getApplicationVersionName()
        withContext(Dispatchers.Main) {
            applicationVersion.value = version
        }
    }
}