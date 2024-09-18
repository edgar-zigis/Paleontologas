package com.zigis.paleontologas.features.settings.routing

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter

class SettingsRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openAboutSection() {
        globalRouter.pushScreen(
            route = SettingsNavGraphRoutes.About.route
        )
    }

    fun openLanguages() {
        globalRouter.pushScreen(
            route = SettingsNavGraphRoutes.Language.route
        )
    }
}