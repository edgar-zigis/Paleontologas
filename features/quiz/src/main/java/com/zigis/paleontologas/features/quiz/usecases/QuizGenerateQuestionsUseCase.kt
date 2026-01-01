package com.zigis.paleontologas.features.quiz.usecases

import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository

import kotlin.math.ceil
import kotlin.math.max

class QuizGenerateQuestionsUseCase(
    private val questionRepository: QuestionRepository
) {
    suspend fun generateRandomQuestions(
        category: Question.Category?, // adjust type if yours is QuizQuestion.Category
        questionCount: Int
    ): List<Question> {
        // 1) Get all questions
        val allQuestions = questionRepository.findAll()

        // 2) Filter by category (Swift logic: null = non-fossil general)
        val filtered = if (category == null) {
            allQuestions.filter { it.getCategory() != Question.Category.FOSSIL }
        } else {
            allQuestions.filter { it.getCategory() == category }
        }

        // 3) Split into two shuffled pools
        val unanswered = filtered.filter { !it.isAnswered }.shuffled()
        val answered = filtered.filter { it.isAnswered }.shuffled()

        // 4) Mandatory quota: 30% rounded up
        val minUnansweredCount = ceil(questionCount * 0.3).toInt()

        // 5) Take mandatory unanswered (safe if not enough)
        val selectedUnanswered = unanswered.take(minUnansweredCount)

        // 6) Fill remaining slots from shuffled combined pool
        val slotsRemaining = max(0, questionCount - selectedUnanswered.size)
        val remainingUnanswered = unanswered.drop(minUnansweredCount)
        val fillerPool = (remainingUnanswered + answered).shuffled()
        val fillerQuestions = fillerPool.take(slotsRemaining)

        // 7) Combine, shuffle, then process variants
        val result = (selectedUnanswered + fillerQuestions).shuffled()
        return processQuestions(result)
    }

    private fun processQuestions(questions: List<Question>): List<Question> {
        questions.forEach {
            it.variantList.addAll(
                listOf(
                    "${it.periodName}_variant_${it.questionIndex}_1",
                    "${it.periodName}_variant_${it.questionIndex}_2",
                    "${it.periodName}_variant_${it.questionIndex}_3",
                    "${it.periodName}_variant_${it.questionIndex}_4"
                )
            )
            it.variantList.shuffle()
        }
        return questions
    }
}