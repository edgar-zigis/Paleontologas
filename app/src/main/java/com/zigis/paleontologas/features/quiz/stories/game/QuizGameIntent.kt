package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent
import com.zigis.paleontologas.features.quiz.data.Question

sealed class QuizGameIntent : IIntent {
    data object Initialize : QuizGameIntent()
    data class AnswerQuestion(val question: Question, val option: Int) : QuizGameIntent()
}