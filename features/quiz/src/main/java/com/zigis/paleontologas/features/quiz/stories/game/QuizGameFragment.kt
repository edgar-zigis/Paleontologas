package com.zigis.paleontologas.features.quiz.stories.game

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameIntent.*
import com.zigis.paleontologas.features.quiz.data.Question
import org.koin.android.ext.android.inject

class QuizGameFragment : BaseFragment<QuizGameViewState, QuizGameIntent, QuizGameViewModel>(),
    QuizGameViewDelegate {

    override val viewModel: QuizGameViewModel by inject()

    override fun onCreateView(context: Context): IView<QuizGameViewState> {
        return QuizGameView(context).also {
            it.delegate = this
        }.also {
            viewModel.intents.sendSafely(Initialize)
        }
    }

    //  QuizGameViewDelegate

    override fun answerQuestion(question: Question, answer: Int) {
        viewModel.intents.sendSafely(AnswerQuestion(
            question = question,
            option = answer
        ))
    }

    override fun onBackInvoked() {
        viewModel.intents.sendSafely(InvokeBack)
    }
}