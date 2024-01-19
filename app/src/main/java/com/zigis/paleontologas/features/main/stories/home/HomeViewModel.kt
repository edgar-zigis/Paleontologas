package com.zigis.paleontologas.features.main.stories.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.library.usecases.PeriodListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel constructor(
    private val periodListUseCase: PeriodListUseCase
) : ViewModel() {

    val periods = MutableLiveData<List<Period>>()

    fun loadPeriods() = viewModelScope.launch(Dispatchers.IO) {
        val items = periodListUseCase.getPeriodItems()
        withContext(Dispatchers.Main) {
            periods.value = items
        }
    }
}