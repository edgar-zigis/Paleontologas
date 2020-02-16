package com.zigis.paleontologas.periods.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.evernote.android.state.State
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.periods.data.entities.LifeForm
import com.zigis.paleontologas.periods.viewmodels.PeriodViewModel
import com.zigis.paleontologas.periods.views.PeriodView
import com.zigis.paleontologas.periods.views.PeriodViewDelegate

class PeriodFragment : BaseViewModelFragment<PeriodViewModel, PeriodView>(), PeriodViewDelegate {

    @State var periodId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): PeriodView {
        return PeriodView(inflater.context).also {
            it.delegate = this
            it.onBack = {
                activity?.onBackPressed()
            }
        }
    }

    override fun onAttached() {
        viewModel.loadPeriod(periodId)
        viewModel.loadLifeForms(periodId)
    }

    override fun observeChanges() {
        viewModel.period.observe(viewLifecycleOwner, Observer {
            contentView.configureWith(it)
        })
        viewModel.lifeForms.observe(viewLifecycleOwner, Observer {
            contentView.setLifeForms(it)
        })
    }

    //  PeriodViewDelegate

    override fun openLifeForm(lifeForm: LifeForm) {
        globalRouter.pushFragment(
            LifeFormFragment().also { it.lifeFormId = lifeForm.id }
        )
    }
}