package com.zigis.paleontologas.core.architecture

import com.zigis.paleontologas.core.routers.GlobalRouter

abstract class BaseRouter(
    protected open val globalRouter: GlobalRouter
) {
    fun popCurrentFragment(isImmediate: Boolean = false, repeatCount: Int = 1) {
        for (i in 0 until repeatCount) {
            globalRouter.popCurrentFragment(isImmediate)
        }
    }
}