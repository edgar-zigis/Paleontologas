package com.zigis.paleontologas.other.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.application.extensions.android.DistinctLiveData
import com.zigis.paleontologas.application.managers.ApplicationLocaleManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

typealias LocaleConfiguration = Pair<Locale?, List<Locale>>

class LanguageViewModel constructor(
    private val applicationLocaleManager: ApplicationLocaleManager
) : ViewModel() {

    val localeConfiguration = DistinctLiveData<LocaleConfiguration>()
    val updatedLocale = DistinctLiveData<Locale>()

    fun loadLocaleList() = viewModelScope.launch(Dispatchers.IO) {
        val configuration = LocaleConfiguration(
            applicationLocaleManager.getCurrentLocale(),
            applicationLocaleManager.getAvailableLocales()
        )
        withContext(Dispatchers.Main) {
            localeConfiguration.value = configuration
        }
    }

    fun changeLocale(locale: Locale) = viewModelScope.launch(Dispatchers.IO) {
        applicationLocaleManager.setCurrentLocale(locale)
        withContext(Dispatchers.Main) {
            updatedLocale.value = locale
        }
    }
}