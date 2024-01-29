package com.zigis.paleontologas.features.quiz.routers

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.quiz.stories.game.QuizGameFragment
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultConfiguration
import com.zigis.paleontologas.features.quiz.stories.finalresult.QuizFinalResultFragment
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressFragment

class QuizRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openQuizProgress() {
        globalRouter.pushFragment(
            QuizProgressFragment()
        )
    }

    fun openQuizGame() {
        globalRouter.pushFragment(
            QuizGameFragment()
        )
    }

    fun openQuizFinalResultPreview(mark: Int) {
        globalRouter.pushFragment(
            QuizFinalResultFragment().also {
                it.configuration = QuizFinalResultConfiguration(
                    mark = mark
                )
            }
        )
    }
}