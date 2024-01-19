package com.zigis.paleontologas.features.quiz.stories.progress

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameFragment

class QuizProgressFragment : BaseFragment<QuizProgressViewModel, QuizProgressView>(),
    QuizProgressViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): QuizProgressView {
        return QuizProgressView(inflater.context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        super.onAttached()
        viewModel.loadProgress()
    }

    override fun observeChanges() {
        viewModel.quizProgress.observe(viewLifecycleOwner) {
            contentView.setProgress(it)
        }
    }

    //  QuizProgressViewDelegate

    override fun onStartQuiz() {
        globalRouter.pushFragment(
            QuizGameFragment()
        )
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}