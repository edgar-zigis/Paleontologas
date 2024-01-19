package com.zigis.paleontologas.features.main.stories.about

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseFragment

class AboutFragment : BaseFragment<AboutViewModel, AboutView>(), AboutViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): AboutView {
        return AboutView(inflater.context).also {
            it.delegate = this
        }.also {
            viewModel.loadApplicationVersion()
        }
    }

    override fun observeChanges() {
        viewModel.applicationVersion.observe(viewLifecycleOwner) {
            contentView.setApplicationVersion(it)
        }
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}