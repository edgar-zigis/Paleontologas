package com.zigis.paleontologas.features.main.stories.tabnavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zigis.paleontologas.features.library.routing.LibraryNavGraphRoutes
import com.zigis.paleontologas.features.library.stories.timeline.TimelineScreen
import com.zigis.paleontologas.features.settings.stories.about.AboutScreen
import com.zigis.paleontologas.features.settings.stories.language.LanguageScreen
import com.zigis.paleontologas.features.main.stories.main.MainBottomNavigationBar
import com.zigis.paleontologas.features.main.stories.main.MenuItem
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressScreen

@Composable
fun TabNavigationScreen() {
    val navController = rememberNavController()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (navHost, bottomNavigation) = createRefs()

        NavHost(
            navController = navController,
            startDestination = LibraryNavGraphRoutes.Timeline.route,
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
            composable(MenuItem.Route.QUIZ.name) {
                QuizProgressScreen()
            }
            composable(MenuItem.Route.LANGUAGE.name) {
                LanguageScreen()
            }
            composable(MenuItem.Route.INFO.name) {
                AboutScreen()
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