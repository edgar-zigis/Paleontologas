package com.zigis.paleontologas.quiz.views

import android.content.Context
import android.content.res.ColorStateList
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.ContextCompat
import com.github.florent37.viewanimator.ViewAnimator
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.runDelayed
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.quiz.data.entities.Question
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_quiz_game.view.*
import uk.co.senab.photoview.PhotoViewAttacher

interface QuizGameViewDelegate {
    fun answerQuestion(question: Question, answer: Int)
}

class QuizGameView(context: Context) : BaseView(context, R.layout.view_quiz_game) {

    var delegate: QuizGameViewDelegate? = null

    private val fadingViews by lazy {
        arrayOf(thumbnail, questionTitle, variant1, variant2, variant3, variant4)
    }

    private val variantButtons by lazy {
        arrayOf(variant1, variant2, variant3, variant4)
    }

    private val vibrator by lazy {
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    override fun initialize() {
        title.text = context.getString(R.string.quiz)
    }

    fun setCurrentQuestion(question: Question) {
        if (questionTitle.text.isNullOrEmpty()) {
            setQuestionValues(question)
            resetButtonStates()
        } else {
            ViewAnimator
                .animate(*fadingViews)
                .alpha(0f)
                .duration(200)
                .onStop {
                    resetButtonStates()
                    setQuestionValues(question)
                }
                .thenAnimate(*fadingViews)
                .alpha(1f)
                .duration(300)
                .start()
        }
    }

    @Suppress("deprecation")
    private fun setQuestionValues(question: Question) {
        thumbnail.setImageDrawable(context.getDrawable(question.artwork))
        PhotoViewAttacher(thumbnail).update()
        questionTitle.text = context.getString(
            "${question.periodName}_question_${question.questionIndex}"
        )
        variantButtons.forEachIndexed { index, button ->
            button.text = context.getString(question.variantList[index])
            button.setDebounceClickListener {
                variantButtons.map { it.isEnabled = false }
                val correctVariant = question.getCorrectVariantIndex()
                if (correctVariant == index) {
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(context, R.color.green)
                    )
                } else {
                    variantButtons[correctVariant].backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(context, R.color.green)
                    )
                    button.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(context, R.color.red)
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(
                            VibrationEffect.createOneShot(
                                300,
                                VibrationEffect.DEFAULT_AMPLITUDE
                            )
                        )
                    } else {
                        vibrator.vibrate(300)
                    }
                }
                runDelayed(1500L) {
                    delegate?.answerQuestion(question, answer = index)
                }
            }
        }
    }

    private fun resetButtonStates() {
        variantButtons.forEach {
            it.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(context, R.color.colorPrimary)
            )
            it.isEnabled = true
        }
    }
}