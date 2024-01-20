package com.zigis.paleontologas.features.library.stories.lifeforms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.features.library.data.LifeForm
import com.zigis.paleontologas.features.library.repositories.LifeFormRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LifeFormViewModel constructor(
    private val lifeFormRepository: LifeFormRepository
) : ViewModel() {

    val lifeForm = DistinctLiveData<LifeForm>()

    fun loadLifeForm(lifeFormId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val fetchedLifeForm = lifeFormRepository.findOne(lifeFormId)
        withContext(Dispatchers.Main) {
            lifeForm.value = fetchedLifeForm
        }
    }
}