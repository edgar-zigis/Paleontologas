package com.zigis.paleontologas.core.architecture.v2

import com.zigis.paleontologas.core.routers.GlobalRouter

abstract class BaseRouter(
    protected open val globalRouter: GlobalRouter
) {
    fun popCurrentFragment() {
        globalRouter.popCurrentFragment()
    }
}