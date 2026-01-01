package com.zigis.paleontologas.features.quiz.stories.categoryselection

import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.features.quiz.factories.QuizCategoryFactory
import com.zigis.paleontologas.features.quiz.managers.PaywallManager
import com.zigis.paleontologas.features.quiz.routing.QuizRouter

class QuizCategoryViewModel(
    private val quizRouter: QuizRouter,
    private val paywallManager: PaywallManager,
    private val quizCategoryFactory: QuizCategoryFactory
) : BaseViewModel<QuizCategoryViewState, QuizCategoryIntent>() {

    override fun getInitialData() = QuizCategoryViewState()

    override suspend fun handleIntent(intent: QuizCategoryIntent) {
        when (intent) {
            QuizCategoryIntent.Initialize -> initialize()
            is QuizCategoryIntent.InvokeBack -> quizRouter.popCurrentScreen()
            is QuizCategoryIntent.ChooseCategory -> {
                if (paywallManager.isPremiumUser() || intent.category == null) {
                    quizRouter.openQuizGame(category = intent.category)
                } else {
                    updateState {
                        it.copy(displayPaywallSheet = true)
                    }
                }
            }
            is QuizCategoryIntent.DismissPaywall -> {
                updateState {
                    it.copy(displayPaywallSheet = false)
                }
                if (intent.attemptPurchase) {
                    paywallManager.launchPurchaseFlow()
                }
            }
        }
    }

    private suspend fun initialize() {
        val items = quizCategoryFactory.getItems()

        val general = items.firstOrNull { it.category == null }
        val tailored = items.filter { it.category != null }

        updateState {
            it.copy(
                generalQuizCategory = general,
                tailoredQuizCategories = tailored
            )
        }
    }
}
