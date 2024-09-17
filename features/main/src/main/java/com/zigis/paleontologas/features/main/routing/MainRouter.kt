package com.zigis.paleontologas.features.main.routing

import android.content.Intent
import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.main.MainActivity

class MainRouter(
    override val globalRouter: GlobalRouter,
    private val androidLifecycleProvider: AndroidLifecycleProvider
) : BaseRouter(globalRouter) {

    fun openMainScreen() {
        val context = androidLifecycleProvider.getActiveContext()
        Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
        androidLifecycleProvider.getActivity()?.finish()
    }
}