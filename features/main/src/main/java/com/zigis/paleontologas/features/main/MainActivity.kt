package com.zigis.paleontologas.features.main

import android.os.Bundle
import com.zigis.paleontologas.core.architecture.BaseActivity
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.main.stories.home.HomeFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val globalRouter: GlobalRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager) {
            if (backStackEntryCount == 0) {
                pushFragment(
                    HomeFragment(),
                    R.id.mainRouterContainer,
                    animated = false
                )
            }
        }

        startListeningForRouteRequests()
    }

    private fun startListeningForRouteRequests() = launch {
        globalRouter.addOnFragmentPushListener(this) {
            pushFragment(it, R.id.mainRouterContainer)
        }
        globalRouter.addOnFragmentPopListener(this) {
            onBackPressed()
        }
    }
}