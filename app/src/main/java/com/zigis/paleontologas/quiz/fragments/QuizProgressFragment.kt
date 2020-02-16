package com.zigis.paleontologas.quiz.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.quiz.viewmodels.QuizProgressViewModel
import com.zigis.paleontologas.quiz.views.QuizProgressView
import com.zigis.paleontologas.quiz.views.QuizProgressViewDelegate

class QuizProgressFragment : BaseViewModelFragment<QuizProgressViewModel, QuizProgressView>(),
    QuizProgressViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizProgressView {
        return QuizProgressView(inflater.context).also {
            it.delegate = this
            it.onBack = {
                activity?.onBackPressed()
            }
        }
    }

    override fun onAttached() {
        super.onAttached()
        viewModel.loadProgress()
    }

    override fun observeChanges() {
        viewModel.quizProgress.observe(viewLifecycleOwner, Observer {
            contentView.setProgress(it)
        })
    }

    //  QuizProgressViewDelegate

    override fun onStartQuiz() {
        globalRouter.pushFragment(
            QuizGameFragment()
        )
    }
}