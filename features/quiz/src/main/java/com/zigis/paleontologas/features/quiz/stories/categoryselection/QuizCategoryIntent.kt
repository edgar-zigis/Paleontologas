package com.zigis.paleontologas.features.quiz.stories.categoryselection

import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import com.zigis.paleontologas.features.quiz.data.Question

sealed class QuizCategoryIntent : IIntent {
    data object Initialize : QuizCategoryIntent()
    data object InvokeBack : QuizCategoryIntent()
    data class ChooseCategory(val category: Question.Category?) : QuizCategoryIntent()
    data class DismissPaywall(val attemptPurchase: Boolean) : QuizCategoryIntent()
}