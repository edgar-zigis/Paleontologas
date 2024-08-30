package com.zigis.paleontologas.features.library.stories.geologicalperiod

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zigis.paleontologas.core.architecture.BaseComposableFragment
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

class GeologicalPeriodFragment : BaseComposableFragment() {

    var configuration: GeologicalPeriodConfiguration? by savedState()

    @Composable
    override fun ViewContentComposition() {
        ApplicationTheme {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                GeologicalPeriodScreen(configuration = configuration!!)
            }
        }
    }
}