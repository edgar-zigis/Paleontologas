package com.zigis.paleontologas.features.library.stories.formavitae

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zigis.paleontologas.core.architecture.BaseComposableFragment
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

class FormaVitaeFragment : BaseComposableFragment() {

    var configuration: FormaVitaeConfiguration? by savedState()

    @Composable
    override fun ViewContentComposition() {
        ApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                FormaVitaeScreen(configuration = configuration!!)
            }
        }
    }
}