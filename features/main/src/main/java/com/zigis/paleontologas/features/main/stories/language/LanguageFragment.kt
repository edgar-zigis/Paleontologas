package com.zigis.paleontologas.features.main.stories.language

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.zigis.paleontologas.core.architecture.BaseComposableFragment
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

class LanguageFragment : BaseComposableFragment() {

    override fun onCreateView(view: ComposeView) {
        with(view) {
            setContent {
                ApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LanguageScreen()
                    }
                }
            }
        }
    }
}