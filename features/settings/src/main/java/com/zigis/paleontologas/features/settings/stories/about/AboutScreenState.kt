package com.zigis.paleontologas.features.settings.stories.about

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.settings.stories.about.list.AboutListItem

data class AboutViewState(
    val contributorItems: List<AboutListItem> = emptyList(),
    val applicationVersion: String = ""
) : IState