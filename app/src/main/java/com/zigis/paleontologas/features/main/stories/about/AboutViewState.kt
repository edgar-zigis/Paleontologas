package com.zigis.paleontologas.features.main.stories.about

import com.zigis.paleontologas.core.architecture.v2.interfaces.IState

data class AboutViewState(
    val applicationVersion: String = ""
) : IState