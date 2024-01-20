package com.zigis.paleontologas.features.launcher

import android.os.Bundle
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseActivity

class LauncherActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }
}