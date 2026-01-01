package com.zigis.paleontologas.features.quiz.factories

import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.entities.QuizCategory
import com.zigis.paleontologas.features.quiz.repositories.QuestionRepository

class QuizCategoryFactory(
    private val questionRepository: QuestionRepository
) {
    suspend fun getItems(): List<QuizCategory> {
        val allQuizQuestions = questionRepository
            .findAll()

        val answeredQuizQuestions = allQuizQuestions.filter { it.isAnswered }

        val nonFossil: (Question) -> Boolean = { it.getCategory() != Question.Category.FOSSIL }

        return listOf(
            QuizCategory(
                id = 0,
                title = R.string.general_category,
                category = null,
                answeredCount = answeredQuizQuestions.count(nonFossil),
                totalCount = allQuizQuestions.count(nonFossil)
            ),
            QuizCategory(
                id = 1,
                title = R.string.fossil_category,
                category = Question.Category.FOSSIL,
                answeredCount = answeredQuizQuestions.count { it.getCategory() == Question.Category.FOSSIL },
                totalCount = allQuizQuestions.count { it.getCategory() == Question.Category.FOSSIL }
            ),
            QuizCategory(
                id = 2,
                title = R.string.precambrian_category,
                category = Question.Category.PRECAMBRIAN,
                answeredCount = answeredQuizQuestions.count { it.getCategory() == Question.Category.PRECAMBRIAN },
                totalCount = allQuizQuestions.count { it.getCategory() == Question.Category.PRECAMBRIAN }
            ),
            QuizCategory(
                id = 3,
                title = R.string.paleozoic_category,
                category = Question.Category.PALEOZOIC,
                answeredCount = answeredQuizQuestions.count { it.getCategory() == Question.Category.PALEOZOIC },
                totalCount = allQuizQuestions.count { it.getCategory() == Question.Category.PALEOZOIC }
            ),
            QuizCategory(
                id = 4,
                title = R.string.mesozoic_category,
                category = Question.Category.MESOZOIC,
                answeredCount = answeredQuizQuestions.count { it.getCategory() == Question.Category.MESOZOIC },
                totalCount = allQuizQuestions.count { it.getCategory() == Question.Category.MESOZOIC }
            ),
            QuizCategory(
                id = 5,
                title = R.string.cenozoic_category,
                category = Question.Category.CENOZOIC,
                answeredCount = answeredQuizQuestions.count { it.getCategory() == Question.Category.CENOZOIC },
                totalCount = allQuizQuestions.count { it.getCategory() == Question.Category.CENOZOIC }
            )
        )
    }
}
