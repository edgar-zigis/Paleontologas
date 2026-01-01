package com.zigis.paleontologas.features.quiz.stories.categoryselection

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.quiz.entities.QuizCategory

data class QuizCategoryViewState(
    val displayPaywallSheet: Boolean = false,
    val generalQuizCategory: QuizCategory? = null,
    val tailoredQuizCategories: List<QuizCategory> = emptyList()
) : IState
