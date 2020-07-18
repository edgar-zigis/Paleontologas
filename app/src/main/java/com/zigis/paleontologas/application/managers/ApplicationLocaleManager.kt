package com.zigis.paleontologas.application.managers

import com.zigis.paleontologas.application.preferences.ApplicationPreferences
import java.util.*

class ApplicationLocaleManager constructor(
    private val applicationPreferences: ApplicationPreferences
) {
    fun getAvailableLocales(): List<Locale> {
        return availableLocales.map {
            Locale(it)
        }
    }

    fun getCurrentLocale(): Locale? {
        if (applicationPreferences.locale != null) {
            return Locale(applicationPreferences.locale!!)
        }
        return null
    }

    fun setCurrentLocale(locale: Locale) {
        applicationPreferences.locale = locale.language
    }

    fun setInitialLocaleIfNeeded(phoneLocale: Locale) {
        if (applicationPreferences.locale == null) {
            val supportedLocales = getAvailableLocales().map { it.language }
            if (phoneLocale.language in supportedLocales) {
                setCurrentLocale(phoneLocale)
            } else {
                setCurrentLocale(Locale(defaultLocale))
            }
        }
    }

    companion object {
        private const val defaultLocale = "en"
        private val availableLocales = listOf(defaultLocale, "de", "es", "lt")
    }
}