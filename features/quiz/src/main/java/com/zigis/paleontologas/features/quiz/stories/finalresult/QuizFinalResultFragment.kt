package com.zigis.paleontologas.features.quiz.stories.finalresult

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultIntent.*
import org.koin.android.ext.android.inject

class QuizFinalResultFragment : BaseFragment<QuizFinalResultViewState, QuizFinalResultIntent, QuizFinalResultViewModel>(),
    QuizFinalResultViewDelegate {

    var configuration: QuizFinalResultConfiguration? by savedState()

    override val viewModel: QuizFinalResultViewModel by inject()

    override fun onCreateView(context: Context): IView<QuizFinalResultViewState> {
        return QuizFinalResultView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(Initialize(
            mark = configuration!!.mark
        ))
    }

    override fun onBackPressed(): Boolean {
        viewModel.intents.sendSafely(InvokeBack)
        return true
    }

    //  QuizMarkViewDelegate

    override fun onBackInvoked() {
        viewModel.intents.sendSafely(InvokeBack)
    }
}