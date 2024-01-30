package com.zigis.paleontologas.features.library.stories.formavitae

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.library.stories.formavitae.FormaVitaeIntent.*
import org.koin.android.ext.android.inject

class FormaVitaeFragment : BaseFragment<FormaVitaeViewState, FormaVitaeIntent, FormaVitaeViewModel>(),
    FormaVitaeViewDelegate {

    var configuration: FormaVitaeConfiguration? by savedState()

    override val viewModel: FormaVitaeViewModel by inject()

    override fun onCreateView(context: Context): IView<FormaVitaeViewState> {
        return FormaVitaeView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(
            Initialize(lifeFormId = configuration!!.lifeFormId)
        )
    }

    //  LifeFormViewDelegate

    override fun onBackInvoked() {
        viewModel.intents.sendSafely(InvokeBack)
    }
}