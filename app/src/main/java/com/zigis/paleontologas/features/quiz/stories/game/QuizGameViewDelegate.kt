package com.zigis.paleontologas.features.quiz.stories.game

import com.zigis.paleontologas.features.quiz.data.Question

interface QuizGameViewDelegate {
    fun answerQuestion(question: Question, answer: Int)
    fun onBackInvoked()
}