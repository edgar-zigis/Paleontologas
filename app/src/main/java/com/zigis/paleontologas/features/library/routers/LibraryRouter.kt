package com.zigis.paleontologas.features.library.routers

import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.library.stories.lifeforms.LifeFormConfiguration
import com.zigis.paleontologas.features.library.stories.lifeforms.LifeFormFragment
import com.zigis.paleontologas.features.library.stories.periods.PeriodConfiguration
import com.zigis.paleontologas.features.library.stories.periods.PeriodFragment

class LibraryRouter(
    override val globalRouter: GlobalRouter
) : BaseRouter(globalRouter) {

    fun openPeriod(periodId: Int) {
        globalRouter.pushFragment(
            PeriodFragment().also {
                it.configuration = PeriodConfiguration(
                    periodId = periodId
                )
            }
        )
    }

    fun openLifeForm(lifeFormId: Int) {
        globalRouter.pushFragment(
            LifeFormFragment().also {
                it.configuration = LifeFormConfiguration(
                    lifeFormId = lifeFormId
                )
            }
        )
    }
}