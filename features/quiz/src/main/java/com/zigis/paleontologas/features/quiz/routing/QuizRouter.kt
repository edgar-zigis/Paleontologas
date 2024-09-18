package com.zigis.paleontologas.features.quiz.routing

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultConfiguration

class QuizRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openQuizGame() {
        globalRouter.pushScreen(
            route = QuizNavGraphRoutes.QuizGame.route
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