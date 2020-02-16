package com.zigis.paleontologas.main.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.periods.data.entities.Period
import com.zigis.paleontologas.periods.usecases.PeriodListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuViewModel constructor(
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