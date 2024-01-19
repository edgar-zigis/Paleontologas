package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizProgressBinding

class QuizProgressView(context: Context) : BaseView(context) {

    var delegate: QuizProgressViewDelegate? = null

    override val binding = ViewQuizProgressBinding.inflate(layoutInflater)

    init {
        with(binding) {
            title.text = getString(R.string.quiz)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            startButton.setDebounceClickListener {
                delegate?.onStartQuiz()
            }
        }
        addView(binding.root)
    }

    fun setProgress(progress: Float) = with(binding) {
        progressBar.setCurrentValues(progress)
    }
}