package com.zigis.paleontologas.quiz.helpers

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.zigis.paleontologas.R

object QuizMarkHelper {

    @RawRes
    fun getAnimationResource(mark: Int): Int {
        return when (mark) {
            10 -> R.raw.quiz_mark_10
            9 -> R.raw.quiz_mark_9
            8 -> R.raw.quiz_mark_8
            7 -> R.raw.quiz_mark_7
            6 -> R.raw.quiz_mark_6
            5 -> R.raw.quiz_mark_5
            4 -> R.raw.quiz_mark_4
            3 -> R.raw.quiz_mark_3
            2 -> R.raw.quiz_mark_2
            1 -> R.raw.quiz_mark_1
            0 -> R.raw.quiz_mark_0
            else -> throw IllegalArgumentException("Incorrect mark")
        }
    }

    @StringRes
    fun getMarkDescription(mark: Int): Int {
        return when (mark) {
            10 -> R.string.result_10
            9 -> R.string.result_9
            8 -> R.string.result_8
            7 -> R.string.result_7
            6 -> R.string.result_6
            5 -> R.string.result_5
            4 -> R.string.result_4
            3 -> R.string.result_3
            2 -> R.string.result_2
            1 -> R.string.result_1
            0 -> R.string.result_0
            else -> throw IllegalArgumentException("Incorrect mark")
        }
    }
}