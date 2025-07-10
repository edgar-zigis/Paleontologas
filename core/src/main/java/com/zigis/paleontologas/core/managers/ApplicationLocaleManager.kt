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
            return Locale.forLanguageTag((applicationPreferences.locale!!))
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
        private val defaultLocale = Locale.forLanguageTag("en")
        private val availableLocales = listOf(
            Locale.forLanguageTag("de"),
            defaultLocale,
            Locale.forLanguageTag("es"),
            Locale.forLanguageTag("fr"),
            Locale.forLanguageTag("it"),
            Locale.forLanguageTag("lt"),
            Locale.Builder().setLanguage("pt").setRegion("BR").build(),
            Locale.forLanguageTag("ru"),
            Locale.forLanguageTag("sl")
        )
    }
}