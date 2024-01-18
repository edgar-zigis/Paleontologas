package com.zigis.paleontologas.other.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.other.viewmodels.AboutViewModel
import com.zigis.paleontologas.other.views.AboutView
import com.zigis.paleontologas.other.views.AboutViewDelegate

class AboutFragment : BaseViewModelFragment<AboutViewModel, AboutView>(), AboutViewDelegate {

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