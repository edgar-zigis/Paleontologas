package com.zigis.paleontologas.features.main.stories.tabnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import com.zigis.paleontologas.features.library.routing.LibraryNavGraphRoutes
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.quiz.routing.QuizNavGraphRoutes
import com.zigis.paleontologas.features.settings.routing.SettingsNavGraphRoutes

data class MainBottomNavigationMenuItem(
    @param:DrawableRes val icon: Int,
    var isSelected: Boolean,
    @param:StringRes val description: Int,
    val animationSpec: FiniteAnimationSpec<Float> = tween(500),
    val route: String
) {
    companion object {
        fun getAll(): List<MainBottomNavigationMenuItem> {
            return listOf(
                MainBottomNavigationMenuItem(
                    icon = R.drawable.ic_ammonite,
                    isSelected = false,
                    description = R.string.geological_time_scale,
                    route = LibraryNavGraphRoutes.Timeline.route
                ),
                MainBottomNavigationMenuItem(
                    icon = R.drawable.ic_quiz,
                    isSelected = false,
                    description = R.string.quiz,
                    route = QuizNavGraphRoutes.QuizProgress.route
                ),
                MainBottomNavigationMenuItem(
                    icon = R.drawable.ic_settings,
                    isSelected = false,
                    description = R.string.settings,
                    route = SettingsNavGraphRoutes.Settings.route
                )
            )
        }
    }
}