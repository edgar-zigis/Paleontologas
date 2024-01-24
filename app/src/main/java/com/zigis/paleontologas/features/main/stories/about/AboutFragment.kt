package com.zigis.paleontologas.features.main.stories.about

import android.content.Context
import com.zigis.paleontologas.core.architecture.v2.BaseFragment
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class AboutFragment : BaseFragment<AboutViewState, AboutIntent, AboutViewModel>(), AboutViewDelegate {

    override val viewModel: AboutViewModel by inject()

    override fun onCreateView(context: Context): IView<AboutViewState> {
        return AboutView(context).also {
            it.delegate = this
        }.also {
            viewModel.intents.sendSafely(AboutIntent.Initialize)
        }
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}