package com.zigis.paleontologas.launcher.activities

import android.os.Bundle
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseActivity

class LauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }
}