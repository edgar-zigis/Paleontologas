package com.zigis.paleontologas.features.quiz.stories.progress

import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent

sealed class QuizProgressIntent : IIntent {
    data object Initialize : QuizProgressIntent()
    data object StartQuiz : QuizProgressIntent()
}