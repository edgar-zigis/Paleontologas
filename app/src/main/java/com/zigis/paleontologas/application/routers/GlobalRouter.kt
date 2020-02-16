package com.zigis.paleontologas.application.routers

import androidx.fragment.app.Fragment
import kotlinx.coroutines.channels.BroadcastChannel

class GlobalRouter {

    val mainRouterChannel = BroadcastChannel<Fragment>(capacity = 1)

    fun pushFragment(fragment: Fragment) = mainRouterChannel.offer(fragment)
}