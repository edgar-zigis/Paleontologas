package com.zigis.paleontologas.features.main.stories.tabnavigation

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zigis.paleontologas.features.library.routing.LibraryNavGraphRoutes
import com.zigis.paleontologas.features.library.stories.timeline.TimelineScreen
import com.zigis.paleontologas.features.quiz.routing.QuizNavGraphRoutes
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressScreen
import com.zigis.paleontologas.features.settings.routing.SettingsNavGraphRoutes
import com.zigis.paleontologas.features.settings.stories.settings.SettingsScreen

@Composable
fun TabNavigationScreen() {
    val navController = rememberNavController()
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: LibraryNavGraphRoutes.Timeline.route

    BackHandler(enabled = currentRoute != LibraryNavGraphRoutes.Timeline.route) {
        when (currentRoute) {
            QuizNavGraphRoutes.QuizProgress.route, SettingsNavGraphRoutes.Settings.route -> {
                navController.navigate(LibraryNavGraphRoutes.Timeline.route) {
                    popUpTo(navController.graph.startDestinationId) { inclusive = false }
                }
            }
            else -> {
                if (!navController.popBackStack()) {
                    backDispatcher?.onBackPressed()
                }
            }
        }
    }

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (navHost, bottomNavigation) = createRefs()

        NavHost(
            navController = navController,
            startDestination = LibraryNavGraphRoutes.Timeline.route,
            enterTransition = {
                fadeIn(animationSpec = tween(250))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(250))
            },
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(navHost) {
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomNavigation.top)
                }
        ) {
            composable(LibraryNavGraphRoutes.Timeline.route) {
                TimelineScreen()
            }
            composable(QuizNavGraphRoutes.QuizProgress.route) {
                QuizProgressScreen()
            }
            composable(SettingsNavGraphRoutes.Settings.route) {
                SettingsScreen()
            }
        }

        MainBottomNavigationBar(
            modifier = Modifier.constrainAs(bottomNavigation) {
                bottom.linkTo(parent.bottom)
            },
            navController = navController
        )
    }
}