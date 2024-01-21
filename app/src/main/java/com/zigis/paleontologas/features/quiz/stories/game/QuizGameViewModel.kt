package com.zigis.paleontologas.features.quiz.stories.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.entities.QuizEndGameResult
import com.zigis.paleontologas.features.quiz.managers.QuizGameProcessor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizGameViewModel constructor(
    private val quizGameProcessor: QuizGameProcessor
) : ViewModel() {

    val currentQuestion = DistinctLiveData<Question>()
    val endResult = DistinctLiveData<QuizEndGameResult>()

    fun generateQuestions() = viewModelScope.launch(Dispatchers.IO) {
        val questions = quizGameProcessor.generateRandomQuestions()
        withContext(Dispatchers.Main) {
            currentQuestion.value = questions.first()
        }
    }

    fun answerQuestion(question: Question, variantIndex: Int) = viewModelScope.launch(Dispatchers.IO) {
        val nextQuestion = quizGameProcessor.answerQuestion(question, variantIndex)
        withContext(Dispatchers.Main) {
            if (nextQuestion == null) {
                endResult.value =
                    QuizEndGameResult(
                        quizGameProcessor.correctAnswers
                    )
            } else {
                currentQuestion.value = nextQuestion!!
            }
        }
    }
}