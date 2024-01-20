package com.zigis.paleontologas.features.library.stories.periods

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.features.library.data.LifeForm
import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.library.repositories.PeriodRepository
import com.zigis.paleontologas.features.library.usecases.LifeFormListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeriodViewModel constructor(
    private val periodRepository: PeriodRepository,
    private val lifeFormListUseCase: LifeFormListUseCase
) : ViewModel() {

    val period = DistinctLiveData<Period>()
    val lifeForms = DistinctLiveData<List<LifeForm>>()

    fun loadPeriod(periodId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val fetchedPeriod = periodRepository.findOne(periodId)
        withContext(Dispatchers.Main) {
            period.value = fetchedPeriod
        }
    }

    fun loadLifeForms(periodId: Int) = viewModelScope.launch(Dispatchers.IO) {
        delay(300)
        val items = lifeFormListUseCase.getPeriodItems(periodId)
        withContext(Dispatchers.Main) {
            lifeForms.value = items
        }
    }
}