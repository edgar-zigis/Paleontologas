package com.zigis.paleontologas.core.architecture

import com.zigis.paleontologas.core.routers.GlobalRouter

abstract class BaseRouter(
    protected open val globalRouter: GlobalRouter
) {
    fun popCurrentFragment() {
        globalRouter.popCurrentFragment()
    }
}