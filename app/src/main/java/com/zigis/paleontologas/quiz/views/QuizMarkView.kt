package com.zigis.paleontologas.quiz.views

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.architecture.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizMarkBinding
import com.zigis.paleontologas.quiz.helpers.QuizMarkHelper
import com.zigis.paleontologas.quiz.managers.QuizGameProcessor

interface QuizMarkViewDelegate {
    fun onBackInvoked()
}

class QuizMarkView(context: Context) : BaseView(context) {

    var delegate: QuizMarkViewDelegate? = null

    override val binding = ViewQuizMarkBinding.inflate(layoutInflater)

    init {
        with(binding) {
            title.text = getString(R.string.finito)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            continueButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
        }
        addView(binding.root)
    }

    fun setEvaluation(mark: Int) = with(binding) {
        animationView.setAnimation(QuizMarkHelper.getAnimationResource(mark))
        finalMark.text = context.getString(
            R.string.quiz_mark_placeholder,
            mark,
            QuizGameProcessor.questionsToGenerateCount
        )
        finalMarkDescription.text = context.getString(QuizMarkHelper.getMarkDescription(mark))
    }
}