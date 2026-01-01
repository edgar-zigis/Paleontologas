package com.zigis.paleontologas.features.quiz.entities

import androidx.annotation.StringRes
import com.zigis.paleontologas.features.quiz.data.Question
import java.util.Locale

data class QuizCategory(
    val id: Int,
    @param:StringRes val title: Int,
    val category: Question.Category?,
    val answeredCount: Int,
    val totalCount: Int
) {
    val progress: Double
        get() = if (totalCount > 0) answeredCount.toDouble() / totalCount.toDouble() else 0.0

    val formattedProgress: String
        get() {
            val format = if (answeredCount == totalCount || answeredCount == 0) "%.0f" else "%.1f"
            return "${String.format(Locale.US, format, progress * 100)} %"
        }
}