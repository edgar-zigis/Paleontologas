package com.zigis.paleontologas.features.quiz.stories.finalresult

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class QuizFinalResultFragment : BaseFragment<QuizFinalResultkViewState, QuizFinalResultIntent, QuizFinalResultViewModel>(),
    QuizFinalResultViewDelegate {

    var configuration: QuizFinalResultConfiguration? by savedState()

    override val viewModel: QuizFinalResultViewModel by inject()

    override fun onCreateView(context: Context): IView<QuizFinalResultkViewState> {
        return QuizFinalResultView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(QuizFinalResultIntent.Initialize(
            mark = configuration!!.mark
        ))
    }

    override fun onBackPressed(): Boolean {
        activity?.supportFragmentManager?.popBackStackImmediate()
        activity?.supportFragmentManager?.popBackStackImmediate()
        return true
    }

    //  QuizMarkViewDelegate

    override fun onBackInvoked() {
        onBackPressed()
    }
}