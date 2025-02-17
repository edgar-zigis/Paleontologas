package com.zigis.paleontologas.features.library.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodConfiguration
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodScreen
import com.zigis.paleontologas.features.library.stories.lifeform.LifeFormConfiguration
import com.zigis.paleontologas.features.library.stories.lifeform.LifeFormScreen

sealed class LibraryNavGraphRoutes(val route: String) {
    data object Timeline : LibraryNavGraphRoutes("geological_timeline")
}

fun NavGraphBuilder.LibraryNavGraphBuilder() {
    composable<GeologicalPeriodConfiguration>() {
        GeologicalPeriodScreen(configuration = it.toRoute())
    }

    composable<LifeFormConfiguration>() {
        LifeFormScreen(configuration = it.toRoute())
    }
}