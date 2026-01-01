package com.zigis.paleontologas.core.preferences

import android.content.SharedPreferences
import com.zigis.paleontologas.core.BuildConfig

class ApplicationPreferences(
    private val preferences: SharedPreferences
) {
    var locale: String?
        get() {
            return preferences.getString(localeId, null)
        }
        set(value) {
            with(preferences.edit()) {
                putString(localeId, value)
                commit()
            }
        }

    var version: String?
        get() {
            return preferences.getString(versionId, null)
        }
        set(value) {
            with(preferences.edit()) {
                putString(versionId, value)
                commit()
            }
        }

    var isVibrationEnabled: Boolean
        get() {
            return preferences.getBoolean(vibrationSettings, true)
        }
        set(value) {
            with(preferences.edit()) {
                putBoolean(vibrationSettings, value)
                commit()
            }
        }

    var isPremiumUser: Boolean
        get() {
            return preferences.getBoolean(premiumUserEnabled, false) || BuildConfig.DEBUG
        }
        set(value) {
            with(preferences.edit()) {
                putBoolean(premiumUserEnabled, value)
                commit()
            }
        }

    companion object {
        private const val localeId = "applicationLocale"
        private const val versionId = "applicationVersion"
        private const val vibrationSettings = "applicationVibrationSettings"
        private const val premiumUserEnabled = "applicationPremiumUserEnabled"
    }
}