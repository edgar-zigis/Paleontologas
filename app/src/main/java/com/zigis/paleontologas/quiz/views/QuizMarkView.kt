package com.zigis.paleontologas.quiz.views

import android.content.Context
import android.view.LayoutInflater
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizMarkBinding
import com.zigis.paleontologas.quiz.helpers.QuizMarkHelper
import com.zigis.paleontologas.quiz.managers.QuizGameProcessor

class QuizMarkView(context: Context) : BaseView<ViewQuizMarkBinding>(
    context,
    ViewQuizMarkBinding.inflate(LayoutInflater.from(context))
) {
    override val titleTextResId: Int = R.string.finito

    override fun initialize() = with(viewBinding) {
        continueButton.setDebounceClickListener {
            onBack?.invoke()
        }
    }

    fun setEvaluation(mark: Int) = with(viewBinding) {
        animationView.setAnimation(QuizMarkHelper.getAnimationResource(mark))
        finalMark.text = context.getString(
            R.string.quiz_mark_placeholder,
            mark,
            QuizGameProcessor.questionsToGenerateCount
        )
        finalMarkDescription.text = context.getString(QuizMarkHelper.getMarkDescription(mark))
    }
}