package com.zigis.paleontologas.quiz.views

import android.content.Context
import android.view.LayoutInflater
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizProgressBinding

interface QuizProgressViewDelegate {
    fun onStartQuiz()
}

class QuizProgressView(context: Context) : BaseView<ViewQuizProgressBinding>(
    context,
    ViewQuizProgressBinding.inflate(LayoutInflater.from(context))
) {
    override val titleTextResId: Int = R.string.quiz

    var delegate: QuizProgressViewDelegate? = null

    override fun initialize() = with(viewBinding) {
        startButton.setDebounceClickListener {
            delegate?.onStartQuiz()
        }
    }

    fun setProgress(progress: Float) = with(viewBinding) {
        progressBar.setCurrentValues(progress)
    }
}