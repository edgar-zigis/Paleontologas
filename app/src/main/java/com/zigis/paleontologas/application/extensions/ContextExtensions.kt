package com.zigis.paleontologas.application.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.math.ceil

fun Context.getDrawable(id: String): Drawable? {
    val resId = resources.getIdentifier(
        id,
        "drawable",
        applicationContext?.packageName
    )
    return if (resId != 0) ContextCompat.getDrawable(this, resId) else null
}

fun Context.getString(id: String): String {
    val resId = resources?.getIdentifier(id, "string", packageName)
    return if (resId != null && resId != 0) {
        getString(resId)
    } else {
        id
    }
}

fun Context.getColorIntWithAlpha(@ColorRes colorId: Int, alpha: Float): Int {
    val alphaValue = ceil(255 * alpha).toInt()
    return ContextCompat.getColor(this, colorId).withAlpha(alphaValue)
}