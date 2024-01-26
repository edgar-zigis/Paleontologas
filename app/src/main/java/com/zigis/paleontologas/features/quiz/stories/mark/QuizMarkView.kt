package com.zigis.paleontologas.features.quiz.stories.mark

import android.content.Context
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.v2.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizMarkBinding
import com.zigis.paleontologas.features.quiz.utilities.QuizMarkUtility
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor

class QuizMarkView(context: Context) : BaseView<QuizMarkViewState, ViewQuizMarkBinding>(context) {

    var delegate: QuizMarkViewDelegate? = null

    override var binding: ViewQuizMarkBinding? = ViewQuizMarkBinding.inflate(layoutInflater)

    init {
        with(requireBinding()) {
            title.text = getString(R.string.finito)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            continueButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
        }
        addView(requireBinding().root)
    }

    override fun render(state: QuizMarkViewState) = with(requireBinding()) {
        animationView.setAnimation(QuizMarkUtility.getAnimationResource(state.mark))
        finalMark.text = context.getString(
            R.string.quiz_mark_placeholder,
            state.mark,
            QuizGameProcessor.questionsToGenerateCount
        )
        finalMarkDescription.text = context.getString(QuizMarkUtility.getMarkDescription(state.mark))
    }
}