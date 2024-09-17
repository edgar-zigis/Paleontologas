package com.zigis.paleontologas.features.main.stories.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import com.zigis.paleontologas.features.library.routing.LibraryNavGraphRoutes
import com.zigis.paleontologas.features.main.R

data class MenuItem(
    @DrawableRes val icon: Int,
    var isSelected: Boolean,
    @StringRes val description: Int,
    val animationSpec: FiniteAnimationSpec<Float> = tween(500),
    val route: String
) {
    enum class Route {
        GEOLOGICAL_TIME_SCALE, QUIZ, LANGUAGE, INFO
    }

    companion object {
        fun getAll(): List<MenuItem> {
            return listOf(
                MenuItem(
                    icon = R.drawable.ic_ammonite,
                    isSelected = false,
                    description = R.string.geological_time_scale,
                    route = LibraryNavGraphRoutes.Timeline.route
                ),
                MenuItem(
                    icon = R.drawable.ic_quiz,
                    isSelected = false,
                    description = R.string.quiz,
                    route = Route.QUIZ.name
                ),
                MenuItem(
                    icon = R.drawable.ic_language,
                    isSelected = false,
                    description = R.string.language,
                    route = Route.LANGUAGE.name
                ),
                MenuItem(
                    icon = R.drawable.ic_info,
                    isSelected = false,
                    description = R.string.info,
                    route = Route.INFO.name
                )
            )
        }
    }
}