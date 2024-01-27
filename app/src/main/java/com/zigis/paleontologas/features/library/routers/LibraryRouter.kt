package com.zigis.paleontologas.features.library.routers

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.stories.formavitae.FormaVitaeConfiguration
import com.zigis.paleontologas.features.library.stories.formavitae.FormaVitaeFragment
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodConfiguration
import com.zigis.paleontologas.features.library.stories.geologicalperiod.GeologicalPeriodFragment

class LibraryRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openPeriod(periodId: Int) {
        globalRouter.pushFragment(
            GeologicalPeriodFragment().also {
                it.configuration = GeologicalPeriodConfiguration(
                    periodId = periodId
                )
            }
        )
    }

    fun openLifeForm(lifeFormId: Int) {
        globalRouter.pushFragment(
            FormaVitaeFragment().also {
                it.configuration = FormaVitaeConfiguration(
                    lifeFormId = lifeFormId
                )
            }
        )
    }
}