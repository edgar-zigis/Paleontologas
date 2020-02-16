package com.zigis.paleontologas.quiz.views

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.quiz.helpers.QuizMarkHelper
import com.zigis.paleontologas.quiz.managers.QuizGameProcessor
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_quiz_mark.view.*

class QuizMarkView(context: Context) : BaseView(context, R.layout.view_quiz_mark) {

    override fun initialize() {
        title.text = context.getString(R.string.finito)
        continueButton.setDebounceClickListener {
            onBack?.invoke()
        }
    }

    fun setEvaluation(mark: Int) {
        animationView.setAnimation(QuizMarkHelper.getAnimationResource(mark))
        finalMark.text = context.getString(
            R.string.quiz_mark_placeholder,
            mark,
            QuizGameProcessor.questionsToGenerateCount
        )
        finalMarkDescription.text = context.getString(QuizMarkHelper.getMarkDescription(mark))
    }
}