package com.zigis.paleontologas.quiz.views

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.res.ColorStateList
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.core.content.ContextCompat
import com.github.florent37.viewanimator.ViewAnimator
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.architecture.BaseView
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.runDelayed
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewQuizGameBinding
import com.zigis.paleontologas.quiz.data.entities.Question
import uk.co.senab.photoview.PhotoViewAttacher

interface QuizGameViewDelegate {
    fun answerQuestion(question: Question, answer: Int)
    fun onBackInvoked()
}

class QuizGameView(context: Context) : BaseView(context) {

    var delegate: QuizGameViewDelegate? = null

    override val binding = ViewQuizGameBinding.inflate(layoutInflater)

    private val fadingViews by lazy {
        arrayOf(
            binding.thumbnail,
            binding.questionTitle,
            binding.variant1,
            binding.variant2,
            binding.variant3,
            binding.variant4
        )
    }

    private val variantButtons by lazy {
        arrayOf(
            binding.variant1,
            binding.variant2,
            binding.variant3,
            binding.variant4
        )
    }

    private val vibrator by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
    }

    init {
        with(binding) {
            title.text = getString(R.string.about_app)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
        }
        addView(binding.root)
    }

    fun setCurrentQuestion(question: Question) = with(binding) {
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
    private fun setQuestionValues(question: Question) = with(binding) {
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