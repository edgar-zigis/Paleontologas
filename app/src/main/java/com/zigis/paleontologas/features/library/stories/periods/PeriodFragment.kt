package com.zigis.paleontologas.features.library.stories.periods

import android.content.Context
import com.evernote.android.state.State
import com.zigis.paleontologas.core.architecture.v2.BaseFragment
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.library.data.LifeForm
import org.koin.android.ext.android.inject

class PeriodFragment : BaseFragment<PeriodViewState, PeriodIntent, PeriodViewModel>(), PeriodViewDelegate {

    @State var periodId = 0

    override val viewModel: PeriodViewModel by inject()

    override fun onCreateView(context: Context): IView<PeriodViewState> {
        return PeriodView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            PeriodIntent.Initialize(periodId = periodId)
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