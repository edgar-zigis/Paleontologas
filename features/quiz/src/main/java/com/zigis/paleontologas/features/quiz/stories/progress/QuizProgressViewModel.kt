package com.zigis.paleontologas.features.quiz.stories.progress

import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.zigis.paleontologas.core.architecture.BaseViewModel
import com.zigis.paleontologas.core.extensions.authStateFlow
import com.zigis.paleontologas.features.quiz.managers.CountryManager
import com.zigis.paleontologas.features.quiz.managers.FirebaseDataManager
import com.zigis.paleontologas.features.quiz.routing.QuizRouter
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressIntent.*
import kotlinx.coroutines.launch

class QuizProgressViewModel(
    private val quizRouter: QuizRouter,
    private val firebaseAuth: FirebaseAuth,
    private val countryManager: CountryManager,
    private val firebaseDataManager: FirebaseDataManager,
    private val quizProgressUseCase: QuizProgressUseCase
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
            is StartQuiz -> quizRouter.openQuizGame()
            is InvokeBack -> quizRouter.popCurrentScreen()
        }
    }

    private suspend fun initialize() {
        updateState {
            it.copy(
                progress = quizProgressUseCase.getFullProgress()
            )
        }
    }
}