package com.zigis.paleontologas.features.main.stories.about

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.main.stories.about.AboutIntent.*
import org.koin.android.ext.android.inject

class AboutFragment : BaseFragment<AboutViewState, AboutIntent, AboutViewModel>(),
    AboutViewDelegate {

    override val viewModel: AboutViewModel by inject()

    override fun onCreateView(context: Context): IView<AboutViewState> {
        return AboutView(context).also {
            it.delegate = this
        }.also {
            viewModel.intents.sendSafely(Initialize)
        }
    }

    override fun onBackInvoked() {
        viewModel.intents.sendSafely(InvokeBack)
    }
}