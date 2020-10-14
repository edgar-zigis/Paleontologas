package com.zigis.paleontologas.other.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.other.viewmodels.AboutViewModel
import com.zigis.paleontologas.other.views.AboutView

class AboutFragment : BaseViewModelFragment<AboutViewModel, AboutView>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): AboutView {
        return AboutView(inflater.context).also {
            it.onBack = {
                activity?.onBackPressed()
            }
        }.also {
            viewModel.loadApplicationVersion()
        }
    }

    override fun observeChanges() {
        viewModel.applicationVersion.observe(viewLifecycleOwner) {
            contentView.setApplicationVersion(it)
        }
    }
}