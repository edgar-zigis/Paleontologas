package com.zigis.paleontologas.quiz.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.application.extensions.android.DistinctLiveData
import com.zigis.paleontologas.quiz.usecases.QuizProgressUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizProgressViewModel constructor(
    private val quizProgressUseCase: QuizProgressUseCase
) : ViewModel() {

    val quizProgress = DistinctLiveData<Float>()

    fun loadProgress() = viewModelScope.launch(Dispatchers.IO) {
        val progress = quizProgressUseCase.getFullProgress()
        withContext(Dispatchers.Main) {
            quizProgress.value = progress
        }
    }
}