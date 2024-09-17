package com.zigis.paleontologas.listeners

import com.zigis.paleontologas.core.events.LocaleChangedEvent
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import com.zigis.paleontologas.features.main.routing.MainRouter
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LocaleChangedListener(
    private val mainRouter: MainRouter,
    private val applicationLocaleManager: ApplicationLocaleManager
) {
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: LocaleChangedEvent) {
        applicationLocaleManager.setCurrentLocale(event.locale)
        mainRouter.openMainScreen()
    }
}