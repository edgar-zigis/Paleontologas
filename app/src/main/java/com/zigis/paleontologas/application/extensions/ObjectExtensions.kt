package com.zigis.paleontologas.application.extensions

import android.os.Handler

fun runDelayed(delayMillis: Long, action: () -> Unit) {
    Handler().postDelayed(Runnable(action), delayMillis)
}