package com.zigis.paleontologas.features.quiz.stories.progress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zigis.paleontologas.core.extensions.android.DistinctLiveData
import com.zigis.paleontologas.features.quiz.usecases.QuizProgressUseCase
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