package com.zigis.paleontologas.features.main.stories.about

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class AboutIntent : IIntent {
    data object Initialize : AboutIntent()
    data object InvokeBack : AboutIntent()
}