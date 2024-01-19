package com.zigis.paleontologas.features.library.stories.periods

import android.view.LayoutInflater
import android.view.ViewGroup
import com.evernote.android.state.State
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.features.library.data.LifeForm
import com.zigis.paleontologas.features.library.stories.lifeforms.LifeFormFragment

class PeriodFragment : BaseFragment<PeriodViewModel, PeriodView>(), PeriodViewDelegate {

    @State var periodId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): PeriodView {
        return PeriodView(inflater.context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.loadPeriod(periodId)
        viewModel.loadLifeForms(periodId)
    }

    override fun observeChanges() {
        viewModel.period.observe(viewLifecycleOwner) {
            contentView.configureWith(it)
        }
        viewModel.lifeForms.observe(viewLifecycleOwner) {
            contentView.setLifeForms(it)
        }
    }

    //  PeriodViewDelegate

    override fun openLifeForm(lifeForm: LifeForm) {
        globalRouter.pushFragment(
            LifeFormFragment().also { it.lifeFormId = lifeForm.id }
        )
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}