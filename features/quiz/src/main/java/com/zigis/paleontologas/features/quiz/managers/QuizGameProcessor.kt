package com.zigis.paleontologas.features.quiz.managers

import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository
import com.zigis.paleontologas.features.quiz.usecases.QuizGenerateQuestionsUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class QuizGameProcessor(
    private val questionRepository: QuestionRepository,
    private val firebaseDataManager: FirebaseDataManager,
    private val quizGenerateQuestionsUseCase: QuizGenerateQuestionsUseCase
) {
    companion object {
        const val questionsToGenerateCount = 10
    }

    var correctAnswers = 0
        private set

    private var generatedQuestions = listOf<Question>()

    suspend fun generateRandomQuestions(): List<Question> {
        correctAnswers = 0
        generatedQuestions = quizGenerateQuestionsUseCase.generateRandomQuestions(
            questionCount = questionsToGenerateCount
        )
        return generatedQuestions
    }

    suspend fun answerQuestion(question: Question, variantIndex: Int): Question? {
        if (question.getCorrectVariantIndex() == variantIndex) {
            question.isAnswered = true
            correctAnswers += 1
        }
        val nextQuestionIndex = generatedQuestions.indexOf(question) + 1
        return if (nextQuestionIndex < generatedQuestions.size) {
            generatedQuestions[nextQuestionIndex]
        } else {
            finalize()
            null
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private suspend fun finalize() {
        coroutineScope {
            generatedQuestions.map {
                async {
                    questionRepository.update(it)
                }
            }.awaitAll()
            if (firebaseDataManager.isAuthenticated()) {
                GlobalScope.launch {
                    firebaseDataManager.addXP(
                        if (correctAnswers == 10) 20 else correctAnswers
                    )
                }
            }
        }
    }
}