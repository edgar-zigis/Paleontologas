package com.zigis.paleontologas.application.extensions

import android.os.Handler

@Suppress("Deprecation")
fun runDelayed(delayMillis: Long, action: () -> Unit) {
    Handler().postDelayed(Runnable(action), delayMillis)
}