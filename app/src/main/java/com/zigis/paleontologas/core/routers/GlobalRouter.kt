package com.zigis.paleontologas.core.routers

import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.MutableSharedFlow

class GlobalRouter {

    val mainRouterChannel = MutableSharedFlow<Fragment>(extraBufferCapacity = 10)

    @Suppress("Deprecation")
    fun pushFragment(fragment: Fragment) = mainRouterChannel.tryEmit(fragment)
}