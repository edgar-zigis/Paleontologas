package com.zigis.paleontologas.features.settings.stories.about.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AboutListItem(
    @param:DrawableRes
    val photoResId: Int,
    @param:StringRes
    val fullNameResId: Int,
    @param:StringRes
    val contributionResId: Int
)