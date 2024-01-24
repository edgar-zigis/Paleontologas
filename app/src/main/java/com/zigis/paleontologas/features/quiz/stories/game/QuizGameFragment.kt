package com.zigis.paleontologas.features.quiz.stories.game

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.features.quiz.stories.mark.QuizMarkFragment
import com.zigis.paleontologas.features.quiz.data.Question

class QuizGameFragment : BaseFragment<QuizGameViewModel, QuizGameView>(),
    QuizGameViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizGameView {
        return QuizGameView(inflater.context).also {
            it.delegate = this
        }.also {
            viewModel.generateQuestions()
        }
    }

    override fun observeChanges() {
        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            contentView.setCurrentQuestion(it)
        }
        viewModel.endResult.observe(viewLifecycleOwner) { result ->
            globalRouter.pushFragment(
                QuizMarkFragment().also { it.mark = result.correctAnswers }
            )
        }
    }

    //  QuizGameViewDelegate

    override fun answerQuestion(question: Question, answer: Int) {
        viewModel.answerQuestion(question, answer)
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}