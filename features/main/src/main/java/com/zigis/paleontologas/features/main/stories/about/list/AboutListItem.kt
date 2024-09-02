package com.zigis.paleontologas.features.main.stories.about.list

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AboutListItem(
    @DrawableRes
    val photoResId: Int,
    @StringRes
    val fullNameResId: Int,
    @StringRes
    val contributionResId: Int
)