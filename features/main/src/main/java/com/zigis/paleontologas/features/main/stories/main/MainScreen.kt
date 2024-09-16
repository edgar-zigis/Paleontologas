package com.zigis.paleontologas.features.main.stories.main

import android.content.res.Configuration
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.items.wigglebutton.WiggleButton
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.main.stories.about.AboutScreen
import com.zigis.paleontologas.features.main.stories.home.HomeScreen
import com.zigis.paleontologas.features.main.stories.language.LanguageScreen
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressScreen

@Composable
fun MainScreen(
    navController: NavHostController
) {
    ConstraintLayout(
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
        val (navHost, bottomNavigation) = createRefs()

        NavHost(
            navController = navController,
            startDestination = MenuItem.Route.GEOLOGICAL_TIME_SCALE.name,
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(navHost) {
                    top.linkTo(parent.top)
                    bottom.linkTo(bottomNavigation.top)
                }
        ) {
            composable(MenuItem.Route.GEOLOGICAL_TIME_SCALE.name) {
                HomeScreen()
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

        BottomNavigationBar(
            modifier = Modifier.constrainAs(bottomNavigation) {
                bottom.linkTo(parent.bottom)
            },
            navController = navController
        )
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    navController: NavHostController
) {
    var selectedItem by remember { mutableIntStateOf(0) }

    AnimatedNavigationBar(
        modifier = modifier
            .height(85.dp),
        selectedIndex = selectedItem,
        barBrush = Brush.linearGradient(
            colors = listOf(
                ApplicationTheme.colors.backgroundSecondary,
                ApplicationTheme.colors.backgroundPrimary
            )
        ),
        ballColor = ApplicationTheme.colors.backgroundSecondary,
        ballAnimation = Teleport(tween(500, easing = LinearEasing)),
        indentAnimation = Height(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(
                1000,
                easing = { OvershootInterpolator().getInterpolation(it) }
            )
        )
    ) {
        MenuItem.getAll().forEachIndexed { index, it ->
            WiggleButton(
                modifier = Modifier.fillMaxSize(),
                isSelected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = it.icon,
                backgroundIcon = it.backgroundIcon,
                outlineColor = ApplicationTheme.colors.tintColor,
                contentDescription = stringResource(id = it.description),
                enterExitAnimationSpec = tween(durationMillis = 500, easing = LinearEasing),
                wiggleAnimationSpec = spring(dampingRatio = .45f, stiffness = 35f)
            )
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