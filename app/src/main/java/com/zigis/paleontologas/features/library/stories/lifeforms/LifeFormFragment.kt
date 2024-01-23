package com.zigis.paleontologas.features.library.stories.lifeforms

import android.content.Context
import com.evernote.android.state.State
import com.zigis.paleontologas.core.architecture.v2.BaseFragment
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class LifeFormFragment : BaseFragment<LifeFormViewState, LifeFormIntent, LifeFormViewModel>(), LifeFormViewDelegate {

    @State var lifeFormId = 0

    override val viewModel: LifeFormViewModel by inject()

    override fun onCreateView(context: Context): IView<LifeFormViewState> {
        return LifeFormView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            LifeFormIntent.Initialize(lifeFormId = lifeFormId)
        )
    }

    //  LifeFormViewDelegate

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}