package com.zigis.paleontologas.features.library.stories.lifeforms

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class LifeFormFragment : BaseFragment<LifeFormViewState, LifeFormIntent, LifeFormViewModel>(), LifeFormViewDelegate {

    var configuration: LifeFormConfiguration? by savedState()

    override val viewModel: LifeFormViewModel by inject()

    override fun onCreateView(context: Context): IView<LifeFormViewState> {
        return LifeFormView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            LifeFormIntent.Initialize(lifeFormId = configuration!!.lifeFormId)
        )
    }

    //  LifeFormViewDelegate

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}