package com.zigis.paleontologas.features.main.stories.home

interface HomeViewDelegate {
    fun openLanguages()
    fun openAbout()
    fun openQuiz()
    fun openPeriod(periodId: Int)
}