package com.zigis.paleontologas.application.routers

import androidx.fragment.app.Fragment
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

@OptIn(ObsoleteCoroutinesApi::class)
class GlobalRouter {

    val mainRouterChannel = BroadcastChannel<Fragment>(capacity = 1)

    @Suppress("Deprecation")
    fun pushFragment(fragment: Fragment) = mainRouterChannel.trySend(fragment)
}