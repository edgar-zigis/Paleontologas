package com.zigis.paleontologas.core.managers

import com.zigis.paleontologas.core.preferences.ApplicationPreferences
import java.util.*

class ApplicationLocaleManager(
    private val applicationPreferences: ApplicationPreferences
) {
    fun getAvailableLocales(): List<Locale> {
        return availableLocales
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
                setCurrentLocale(defaultLocale)
            }
        }
    }

    companion object {
        private val defaultLocale = Locale("en")
        private val availableLocales = listOf(
            Locale("de"),
            defaultLocale,
            Locale("es"),
            Locale("fr"),
            Locale("it"),
            Locale("lt"),
            Locale("pt", "br"),
            Locale("sl")
        )
    }
}