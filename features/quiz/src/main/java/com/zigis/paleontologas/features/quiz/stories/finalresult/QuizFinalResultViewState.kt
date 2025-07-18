package com.zigis.paleontologas.features.quiz.stories.finalresult

import com.zigis.paleontologas.core.architecture.interfaces.IState

data class QuizFinalResultViewState(
    val mark: Int = 0,
    val totalQuestions: Int = 0,
    val isParticipatingInLeaderboard: Boolean = false
) : IState