package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizProgressBinding

class QuizProgressView(context: Context) : BaseView<QuizProgressViewState, ViewQuizProgressBinding>(context) {

    var delegate: QuizProgressViewDelegate? = null

    override var binding: ViewQuizProgressBinding? = ViewQuizProgressBinding.inflate(layoutInflater)

    init {
        with(requireBinding()) {
            title.text = getString(R.string.quiz)
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