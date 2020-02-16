package com.zigis.paleontologas.quiz.views

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_quiz_progress.view.*

interface QuizProgressViewDelegate {
    fun onStartQuiz()
}

class QuizProgressView(context: Context) : BaseView(context, R.layout.view_quiz_progress) {

    var delegate: QuizProgressViewDelegate? = null

    override fun initialize() {
        title.text = context.getString(R.string.quiz)
        startButton.setDebounceClickListener {
            delegate?.onStartQuiz()
        }
    }

    fun setProgress(progress: Float) {
        progressBar.setCurrentValues(progress)
    }
}