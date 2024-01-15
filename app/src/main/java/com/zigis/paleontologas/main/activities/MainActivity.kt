package com.zigis.paleontologas.main.activities

import android.os.Bundle
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseActivity
import com.zigis.paleontologas.application.routers.GlobalRouter
import com.zigis.paleontologas.main.fragments.MainMenuFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                    MainMenuFragment(),
                    R.id.mainRouterContainer,
                    animated = false
                )
            }
        }

        startListeningForRouteRequests()
    }

    private fun startListeningForRouteRequests() = launch {
        globalRouter.mainRouterChannel.onEach {
            pushFragment(it, R.id.mainRouterContainer)
        }.launchIn(this)
    }
}