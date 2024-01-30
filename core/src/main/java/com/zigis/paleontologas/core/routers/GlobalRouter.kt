package com.zigis.paleontologas.core.routers

import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class GlobalRouter {

    private val routerBackStackFlow = MutableSharedFlow<Fragment>(extraBufferCapacity = 10)
    private val routerPopFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 10)

    fun pushFragment(fragment: Fragment) {
        routerBackStackFlow.tryEmit(fragment)
    }

    fun popCurrentFragment() {
        routerPopFlow.tryEmit(Unit)
    }

    fun addOnFragmentPushListener(scope: CoroutineScope, action: (Fragment) -> Unit) {
        routerBackStackFlow.onEach {
            action.invoke(it)
        }.launchIn(scope)
    }

    fun addOnFragmentPopListener(scope: CoroutineScope, action: () -> Unit) {
        routerPopFlow.onEach {
            action.invoke()
        }.launchIn(scope)
    }
}