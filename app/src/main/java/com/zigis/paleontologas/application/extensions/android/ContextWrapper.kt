package com.zigis.paleontologas.application.extensions.android

import android.content.Context
import android.os.Build
import android.os.LocaleList
import java.util.*

class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {

    companion object {
        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            var newContext = context
            val configuration = newContext.resources.configuration
            configuration.setLocale(newLocale)
            Locale.setDefault(newLocale)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            }

            newContext = newContext.createConfigurationContext(configuration)
            return ContextWrapper(newContext)
        }
    }
}