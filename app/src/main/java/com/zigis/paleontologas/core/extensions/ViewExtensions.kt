package com.zigis.paleontologas.core.extensions

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun View.setDebounceClickListener(debounceTime: Long = 600L, action: () -> Unit) {

    setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun View.isRTL(): Boolean {
    return resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
}