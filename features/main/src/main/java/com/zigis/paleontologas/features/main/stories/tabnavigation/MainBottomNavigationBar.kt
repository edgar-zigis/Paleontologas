package com.zigis.paleontologas.features.main.stories.tabnavigation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.items.wigglebutton.WiggleButton
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

@Composable
fun MainBottomNavigationBar(
    modifier: Modifier,
    navController: NavHostController
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val menuItems = MainBottomNavigationMenuItem.getAll()

    val selectedItem = menuItems.indexOfFirst {
        it.route == currentRoute
    }.takeIf { it != -1 } ?: 0

    AnimatedNavigationBar(
        modifier = modifier.height(85.dp),
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
        menuItems.forEachIndexed { index, it ->
            WiggleButton(
                modifier = Modifier.fillMaxSize(),
                isSelected = selectedItem == index,
                onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.startDestinationId) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = it.icon,
                outlineColor = ApplicationTheme.colors.tintColor,
                contentDescription = stringResource(id = it.description),
                enterExitAnimationSpec = tween(durationMillis = 500, easing = LinearEasing),
                wiggleAnimationSpec = spring(dampingRatio = .45f, stiffness = 35f)
            )
        }
    }
}