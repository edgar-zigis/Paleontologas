package com.zigis.paleontologas.features.launcher.stories.main

import android.content.Context
import android.widget.Toast
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.databinding.FragmentLauncherBinding

class LauncherView(context: Context) : BaseView<LauncherViewState, FragmentLauncherBinding>(context) {

    override var binding: FragmentLauncherBinding? = FragmentLauncherBinding.inflate(layoutInflater)

    init {
        addView(requireBinding().root)
    }

    override fun render(state: LauncherViewState) {
        state.errorMessage?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}