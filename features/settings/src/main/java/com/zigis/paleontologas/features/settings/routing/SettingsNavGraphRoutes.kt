package com.zigis.paleontologas.features.settings.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.zigis.paleontologas.core.ui.theme.NavigationTransitions
import com.zigis.paleontologas.features.settings.stories.about.AboutScreen
import com.zigis.paleontologas.features.settings.stories.language.LanguageScreen

sealed class SettingsNavGraphRoutes(val route: String) {
    data object Settings : SettingsNavGraphRoutes("settings")
    data object Language : SettingsNavGraphRoutes("settings_language")
    data object About : SettingsNavGraphRoutes("settings_about")
}

fun NavGraphBuilder.SettingsNavGraphBuilder() {
    composable(
        route = SettingsNavGraphRoutes.Language.route,
        enterTransition = NavigationTransitions.enterTransition,
        exitTransition = NavigationTransitions.exitTransition
    ) {
        LanguageScreen()
    }
    composable(
        route = SettingsNavGraphRoutes.About.route,
        enterTransition = NavigationTransitions.enterTransition,
        exitTransition = NavigationTransitions.exitTransition
    ) {
        AboutScreen()
    }
}