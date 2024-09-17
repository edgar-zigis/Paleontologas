package com.zigis.paleontologas.features.main.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zigis.paleontologas.features.main.stories.tabnavigation.TabNavigationScreen

sealed class MainNavGraphRoutes(val route: String) {
    data object TabNavigation : MainNavGraphRoutes("tab_navigation")
}

fun NavGraphBuilder.MainNavGraphBuilder() {
    composable(MainNavGraphRoutes.TabNavigation.route) {
        TabNavigationScreen()
    }
}