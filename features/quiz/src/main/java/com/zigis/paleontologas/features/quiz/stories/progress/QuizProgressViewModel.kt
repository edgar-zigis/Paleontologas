package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.extensions.authStateFlow
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer
import com.zigis.paleontologas.features.quiz.factories.QuizPlayerFactory
import com.zigis.paleontologas.features.quiz.managers.CountryManager
import com.zigis.paleontologas.features.quiz.managers.FirebaseDataManager
import com.zigis.paleontologas.features.quiz.managers.PaywallManager
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import com.zigis.paleontologas.features.quiz.routing.QuizRouter
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressIntent.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizProgressViewModel(
    private val applicationContext: Context,
    private val quizRouter: QuizRouter,
    private val firebaseAuth: FirebaseAuth,
    private val countryManager: CountryManager,
    private val paywallManager: PaywallManager,
    private val quizPlayerFactory: QuizPlayerFactory,
    private val questionRepository: QuestionRepository,
    private val firebaseDataManager: FirebaseDataManager,
    private val quizProgressUseCase: QuizProgressUseCase,
) : BaseViewModel<QuizProgressViewState, QuizProgressIntent>() {

    override fun getInitialData() = QuizProgressViewState()

    init {
        viewModelScope.launch {
            firebaseAuth.authStateFlow()
                .collect { user ->
                    firebaseDataManager.user = user

                    val countries = countryManager.allCountries
                    val needsUsername = user?.let {
                        !firebaseDataManager.leaderboardEntryAlreadyExists()
                    } ?: false

                    updateState {
                        it.copy(
                            activeUser = user,
                            allCountries = countries,
                            createUserNameNeeded = needsUsername
                        )
                    }
                }
        }
    }

    override suspend fun handleIntent(intent: QuizProgressIntent) {
        when (intent) {
            is Initialize -> initialize()
            is StartQuiz -> quizRouter.openCategorySelection()
            is CreateAccount -> createAccount()
            is SetUsername -> setUsername(intent.value, intent.countryCode)
            is InvokeBack -> quizRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize() {
        try {
            val players = quizPlayerFactory.getItems()
            updateState {
                it.copy(
                    players = players,
                    globalRanking = getGlobalRanking(players),
                    totalQuestions = questionRepository.findAll().size,
                    progress = quizProgressUseCase.getFullProgress()
                )
            }
        } catch (exception: Exception) {
            Toast.makeText(applicationContext, exception.message, Toast.LENGTH_LONG).show()
            updateState {
                it.copy(
                    progress = quizProgressUseCase.getFullProgress()
                )
            }
        }
    }

    private suspend fun createAccount() {
        val isPremiumUser = paywallManager.isPremiumUser()
        val productPurchased = paywallManager.launchPurchaseFlow()
        if (isPremiumUser || productPurchased) {
            delay(400)
            try {
                val players = quizPlayerFactory.getItems()
                updateState {
                    it.copy(
                        activeUser = firebaseDataManager.authenticate(),
                        players = players,
                        globalRanking = getGlobalRanking(players),
                        createUserNameNeeded = firebaseDataManager.needsUsername()
                    )
                }
            } catch (exception: Exception) { }
        }
    }

    private suspend fun setUsername(value: String, countryCode: String) {
        if (!firebaseDataManager.isAuthenticated()) {
            return
        }
        updateState {
            it.copy(
                errorDescription = null,
                isLoading = true
            )
        }

        try {
            firebaseDataManager.createInitialEntryIfNeeded(value, countryCode)
            val players = quizPlayerFactory.getItems()
            updateState {
                it.copy(
                    createUserNameNeeded = false,
                    players = players,
                    globalRanking = getGlobalRanking(players)
                )
            }
        } catch (error: Exception) {
            updateState { it.copy(errorDescription = error.message) }
        }

        updateState {
            it.copy(
                isLoading = false
            )
        }
    }

    private fun getGlobalRanking(players: List<QuizPlayer>): Int {
        return players.indexOfFirst { player ->
            player.id == firebaseDataManager.user?.uid
        } + 1
    }
}