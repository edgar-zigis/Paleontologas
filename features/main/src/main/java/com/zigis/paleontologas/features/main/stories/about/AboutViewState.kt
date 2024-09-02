package com.zigis.paleontologas.features.main.stories.about

import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.features.main.stories.about.list.AboutListItem

data class AboutViewState(
    val contributorItems: List<AboutListItem> = emptyList(),
    val applicationVersion: String = ""
) : IState