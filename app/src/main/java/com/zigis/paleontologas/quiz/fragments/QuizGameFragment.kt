package com.zigis.paleontologas.quiz.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.quiz.data.entities.Question
import com.zigis.paleontologas.quiz.viewmodels.QuizGameViewModel
import com.zigis.paleontologas.quiz.views.QuizGameView
import com.zigis.paleontologas.quiz.views.QuizGameViewDelegate

class QuizGameFragment : BaseViewModelFragment<QuizGameViewModel, QuizGameView>(),
    QuizGameViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizGameView {
        return QuizGameView(inflater.context).also {
            it.delegate = this
            it.onBack = {
                activity?.onBackPressed()
            }
        }.also {
            viewModel.generateQuestions()
        }
    }

    override fun observeChanges() {
        viewModel.currentQuestion.observe(viewLifecycleOwner, Observer {
            contentView.setCurrentQuestion(it)
        })
        viewModel.endResult.observe(viewLifecycleOwner, Observer { result ->
            globalRouter.pushFragment(
                QuizMarkFragment().also { it.mark = result.correctAnswers }
            )
        })
    }

    //  QuizGameViewDelegate

    override fun answerQuestion(question: Question, answer: Int) {
        viewModel.answerQuestion(question, answer)
    }
}