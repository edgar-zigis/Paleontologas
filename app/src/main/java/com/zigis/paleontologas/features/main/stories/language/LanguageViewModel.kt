package com.zigis.paleontologas.features.main.stories.language

import android.content.Intent
import com.zigis.paleontologas.core.architecture.v2.BaseViewModel
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.features.main.MainActivity
import java.util.Locale

typealias LocaleConfiguration = Pair<Locale?, List<Locale>>
class LanguageViewModel(
    private val applicationLocaleManager: ApplicationLocaleManager,
    private val androidLifecycleProvider: AndroidLifecycleProvider
) : BaseViewModel<LanguageViewState, LanguageIntent>() {

    override fun getInitialData() = LanguageViewState()

    override suspend fun handleIntent(intent: LanguageIntent) {
        when (intent) {
            is LanguageIntent.Initialize -> initialize()
            is LanguageIntent.ChangeLocale -> changeLocale(locale = intent.locale)
        }
    }

    val localeConfiguration = DistinctLiveData<LocaleConfiguration>()
    val updatedLocale = DistinctLiveData<Locale>()

    private suspend fun initialize() {
        updateState {
            it.copy(
                currentLocale = applicationLocaleManager.getCurrentLocale(),
                localeList = applicationLocaleManager.getAvailableLocales()
            )
        }
    }

    private fun changeLocale(locale: Locale) {
        applicationLocaleManager.setCurrentLocale(locale)
        with(androidLifecycleProvider) {
            getActivity()?.finish()
            getActivity()?.startActivity(
                Intent(getActivity(), MainActivity::class.java)
            )
        }
    }
}