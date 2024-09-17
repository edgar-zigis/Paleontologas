package com.zigis.paleontologas.features.library.routing

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodConfiguration
import com.zigis.paleontologas.features.library.stories.lifeform.LifeFormConfiguration

class LibraryRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openPeriod(periodId: Int) {
        globalRouter.pushScreen(
            route = GeologicalPeriodConfiguration(
                periodId = periodId
            )
        )
    }

    fun openLifeForm(lifeFormId: Int) {
        globalRouter.pushScreen(
            route = LifeFormConfiguration(
                lifeFormId = lifeFormId
            )
        )
    }
}