package com.zigis.paleontologas.periods.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.periods.viewmodels.LifeFormViewModel
import com.zigis.paleontologas.periods.views.LifeFormView

class LifeFormFragment : BaseViewModelFragment<LifeFormViewModel, LifeFormView>() {

    @State var lifeFormId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): LifeFormView {
        return LifeFormView(inflater.context).also {
            it.onBack = {
                activity?.onBackPressed()
            }
        }
    }

    override fun onAttached() {
        viewModel.loadLifeForm(lifeFormId)
    }

    override fun observeChanges() {
        viewModel.lifeForm.observe(viewLifecycleOwner) {
            contentView.configureWith(it)
        }
    }
}