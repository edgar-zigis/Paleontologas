package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.features.quiz.databinding.FragmentQuizProgressBinding

class QuizProgressView(
    context: Context
) : BaseView<QuizProgressViewState, FragmentQuizProgressBinding>(context) {

    var delegate: QuizProgressViewDelegate? = null

    override var binding: FragmentQuizProgressBinding? = FragmentQuizProgressBinding.inflate(layoutInflater)

    init {
        with(requireBinding()) {
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            startButton.setDebounceClickListener {
                delegate?.onStartQuiz()
            }
        }
        addView(requireBinding().root)
    }

    override fun render(state: QuizProgressViewState) = with(requireBinding()) {
        progressBar.setCurrentValues(state.progress)
    }
}