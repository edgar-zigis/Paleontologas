package com.zigis.paleontologas

import com.zigis.paleontologas.listeners.LocaleChangedListener
import org.greenrobot.eventbus.EventBus

class EventBusInitializer(
    private val eventBus: EventBus,
    private val localeChangedListener: LocaleChangedListener
) {
    fun initialize() {
        eventBus.register(localeChangedListener)
    }
}