package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import com.zigis.paleontologas.features.quiz.data.Question

sealed class QuizGameIntent : IIntent {
    data class Initialize(val category: Question.Category?) : QuizGameIntent()
    data class AnswerQuestion(val question: Question, val option: Int) : QuizGameIntent()
    data object InvokeBack : QuizGameIntent()
}