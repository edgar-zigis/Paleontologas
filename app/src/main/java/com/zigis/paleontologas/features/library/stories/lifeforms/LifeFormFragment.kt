package com.zigis.paleontologas.features.library.stories.lifeforms

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.core.architecture.BaseFragment

class LifeFormFragment : BaseFragment<LifeFormViewModel, LifeFormView>(), LifeFormViewDelegate {

    @State var lifeFormId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): LifeFormView {
        return LifeFormView(inflater.context).also {
            it.delegate = this
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

    //  LifeFormViewDelegate

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}