package com.zigis.paleontologas.features.quiz.routing

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultConfiguration
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameConfiguration

class QuizRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openCategorySelection() {
        globalRouter.pushScreen(
            route = QuizNavGraphRoutes.QuizCategory.route
        )
    }

    fun openQuizGame(category: Question.Category? = null) {
        globalRouter.pushScreen(
            route = QuizGameConfiguration(
                category = category
            )
        )
    }

    fun openQuizFinalResultPreview(mark: Int) {
        globalRouter.pushScreen(
            route = QuizFinalResultConfiguration(
                mark = mark
            )
        )
    }
}