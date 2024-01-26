package com.zigis.paleontologas.features.library.stories.periods

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.library.data.LifeForm
import org.koin.android.ext.android.inject

class PeriodFragment : BaseFragment<PeriodViewState, PeriodIntent, PeriodViewModel>(), PeriodViewDelegate {

    var configuration: PeriodConfiguration? by savedState()

    override val viewModel: PeriodViewModel by inject()

    override fun onCreateView(context: Context): IView<PeriodViewState> {
        return PeriodView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            PeriodIntent.Initialize(periodId = configuration!!.periodId)
        )
    }

    //  PeriodViewDelegate

    override fun openLifeForm(lifeForm: LifeForm) {
        viewModel.intents.sendSafely(
            PeriodIntent.OpenLifeForm(lifeForm = lifeForm)
        )
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}