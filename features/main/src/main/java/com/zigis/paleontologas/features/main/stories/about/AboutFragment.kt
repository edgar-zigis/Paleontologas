package com.zigis.paleontologas.features.main.stories.about

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zigis.paleontologas.core.architecture.BaseComposableFragment
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

class AboutFragment : BaseComposableFragment() {

    @Composable
    override fun ViewContentComposition() {
        ApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                AboutScreen()
            }
        }
    }
}