package com.zigis.paleontologas.features.library.stories.geologicalperiod

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodIntent.*
import org.koin.android.ext.android.inject

class GeologicalPeriodFragment :
    BaseFragment<GeologicalPeriodViewState, GeologicalPeriodIntent, GeologicalPeriodViewModel>(),
    GeologicalPeriodViewDelegate {

    var configuration: GeologicalPeriodConfiguration? by savedState()

    override val viewModel: GeologicalPeriodViewModel by inject()

    override fun onCreateView(context: Context): IView<GeologicalPeriodViewState> {
        return GeologicalPeriodView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            Initialize(periodId = configuration!!.periodId)
        )
    }

    //  PeriodViewDelegate

    override fun openLifeForm(lifeFormId: Int) {
        viewModel.intents.sendSafely(
            OpenLifeForm(lifeFormId = lifeFormId)
        )
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}