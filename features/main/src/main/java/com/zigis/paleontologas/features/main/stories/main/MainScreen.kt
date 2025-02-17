package com.zigis.paleontologas.features.main.stories.main

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.library.routing.LibraryNavGraphBuilder
import com.zigis.paleontologas.features.main.routing.MainNavGraphBuilder
import com.zigis.paleontologas.features.main.routing.MainNavGraphRoutes
import com.zigis.paleontologas.features.quiz.routing.QuizNavGraphBuilder
import com.zigis.paleontologas.features.settings.routing.SettingsNavGraphBuilder

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        ApplicationTheme.colors.backgroundPrimary,
                        ApplicationTheme.colors.backgroundSecondary
                    ),
                    start = Offset(Float.POSITIVE_INFINITY, 0f),
                    end = Offset(0f, Float.POSITIVE_INFINITY),
                )
            )
            .navigationBarsPadding()
    ) {
        NavHost(
            navController = navController,
            startDestination = MainNavGraphRoutes.TabNavigation.route,
            modifier = Modifier.fillMaxSize(),
            enterTransition = { slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
            ) },
            exitTransition = { slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(300)
            ) },
            popEnterTransition = { slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, spring()
            ) },
            popExitTransition = { slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, spring()
            ) }
        ) {
            MainNavGraphBuilder()
            LibraryNavGraphBuilder()
            SettingsNavGraphBuilder()
            QuizNavGraphBuilder()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MainScreenPreview() {
    val navController = rememberNavController()
    MainScreen(
        navController = navController
    )
}