package com.zigis.paleontologas.features.launcher.stories.main

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class LauncherFragment : BaseFragment<LauncherViewState, LauncherIntent, LauncherViewModel>() {

    override val viewModel: LauncherViewModel by inject()

    override fun onCreateView(context: Context): IView<LauncherViewState> {
        return LauncherView(context).also {
            viewModel.intents.sendSafely(LauncherIntent.Initialize)
        }
    }
}