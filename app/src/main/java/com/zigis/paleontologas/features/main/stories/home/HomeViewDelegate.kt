package com.zigis.paleontologas.features.main.stories.home

import com.zigis.paleontologas.features.library.data.Period

interface HomeViewDelegate {
    fun openLanguages()
    fun openAbout()
    fun openQuiz()
    fun openPeriod(period: Period)
}