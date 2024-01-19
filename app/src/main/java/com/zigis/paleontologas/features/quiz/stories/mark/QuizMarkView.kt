package com.zigis.paleontologas.features.quiz.stories.mark

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizMarkBinding
import com.zigis.paleontologas.features.quiz.utilities.QuizMarkUtility
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor

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
        animationView.setAnimation(QuizMarkUtility.getAnimationResource(mark))
        finalMark.text = context.getString(
            R.string.quiz_mark_placeholder,
            mark,
            QuizGameProcessor.questionsToGenerateCount
        )
        finalMarkDescription.text = context.getString(QuizMarkUtility.getMarkDescription(mark))
    }
}