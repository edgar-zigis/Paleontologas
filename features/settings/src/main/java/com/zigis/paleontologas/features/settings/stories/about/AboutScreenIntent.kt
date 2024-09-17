package com.zigis.paleontologas.features.settings.stories.about

import com.zigis.paleontologas.core.architecture.interfaces.IIntent

sealed class AboutScreenIntent : IIntent {
    data object Initialize : AboutScreenIntent()
    data object InvokeBack : AboutScreenIntent()
}