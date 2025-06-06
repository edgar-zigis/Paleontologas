package com.zigis.paleontologas.features.quiz.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultConfiguration
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultScreen
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameScreen
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressScreen

sealed class QuizNavGraphRoutes(val route: String) {
    data object QuizProgress : QuizNavGraphRoutes("quiz_progress")
    data object QuizGame : QuizNavGraphRoutes("quiz_game")
}

fun NavGraphBuilder.QuizNavGraphBuilder() {
    composable(route = QuizNavGraphRoutes.QuizProgress.route) {
        QuizProgressScreen()
    }

    composable(route = QuizNavGraphRoutes.QuizGame.route) {
        QuizGameScreen()
    }

    composable<QuizFinalResultConfiguration>() {
        QuizFinalResultScreen(configuration = it.toRoute())
    }
}