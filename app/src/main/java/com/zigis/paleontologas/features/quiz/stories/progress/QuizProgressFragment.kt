package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.Context
import com.zigis.paleontologas.core.architecture.v2.BaseFragment
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class QuizProgressFragment : BaseFragment<QuizProgressViewState, QuizProgressIntent, QuizProgressViewModel>(),
    QuizProgressViewDelegate {

    override val viewModel: QuizProgressViewModel by inject()

    override fun onCreateView(context: Context): IView<QuizProgressViewState> {
        return QuizProgressView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        viewModel.intents.sendSafely(QuizProgressIntent.Initialize)
    }

    //  QuizProgressViewDelegate

    override fun onStartQuiz() {
        viewModel.intents.sendSafely(QuizProgressIntent.StartQuiz)
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}