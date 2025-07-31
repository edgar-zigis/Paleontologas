package com.zigis.paleontologas.core.extensions

import android.content.Context
import android.os.Build
import java.util.Locale

fun Context.getDrawableId(id: String): Int {
    return resources.getIdentifier(
        id,
        "drawable",
        applicationContext?.packageName
    )
}

fun Context.getString(id: String): String {
    val resId = resources?.getIdentifier(id, "string", packageName)
    return if (resId != null && resId != 0) {
        getString(resId)
    } else {
        id
    }
}

fun Context.getStringId(id: String): Int {
    return resources.getIdentifier(id, "string", packageName)
}

fun Context.activeLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        resources.configuration.locales[0]
    } else {
        @Suppress("DEPRECATION")
        resources.configuration.locale
    }
}