package com.zigis.paleontologas.features.quiz.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zigis.paleontologas.features.quiz.stories.categoryselection.QuizCategoryScreen
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultConfiguration
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultScreen
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameConfiguration
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameScreen
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressScreen

sealed class QuizNavGraphRoutes(val route: String) {
    data object QuizProgress : QuizNavGraphRoutes("quiz_progress")
    data object QuizCategory : QuizNavGraphRoutes("quiz_category")
}

fun NavGraphBuilder.QuizNavGraphBuilder() {
    composable(route = QuizNavGraphRoutes.QuizProgress.route) {
        QuizProgressScreen()
    }

    composable(route = QuizNavGraphRoutes.QuizCategory.route) {
        QuizCategoryScreen()
    }

    composable<QuizGameConfiguration> {
        QuizGameScreen(configuration = it.toRoute())
    }

    composable<QuizFinalResultConfiguration>() {
        QuizFinalResultScreen(configuration = it.toRoute())
    }
}