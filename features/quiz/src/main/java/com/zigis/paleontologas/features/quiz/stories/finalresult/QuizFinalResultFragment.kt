package com.zigis.paleontologas.features.quiz.stories.finalresult

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zigis.paleontologas.core.architecture.BaseComposableFragment
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

class QuizFinalResultFragment : BaseComposableFragment() {

    var configuration: QuizFinalResultConfiguration? by savedState()

    @Composable
    override fun ViewContentComposition() {
        ApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                QuizFinalResultScreen(configuration = configuration!!)
            }
        }
    }
}