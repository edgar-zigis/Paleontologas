package com.zigis.paleontologas.launcher.views

import android.content.Context
import android.widget.FrameLayout
import com.zigis.paleontologas.R

class LauncherView(context: Context) : FrameLayout(context) {

    init {
        inflate(context, R.layout.view_launcher, this)
    }
}