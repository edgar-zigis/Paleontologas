package com.zigis.paleontologas.core.extensions

import android.content.Context

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