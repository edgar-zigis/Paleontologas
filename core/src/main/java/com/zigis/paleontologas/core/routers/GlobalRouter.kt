package com.zigis.paleontologas.core.routers

import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.Serializable

class GlobalRouter {

    private val routerBackStackFlow = MutableSharedFlow<String>(extraBufferCapacity = 10)
    private val routerPopFlow = MutableSharedFlow<String?>(extraBufferCapacity = 10)

    private var navHostController: NavHostController? = null

    fun <T : Serializable> pushScreen(route: T) {
        navHostController?.navigate(route)
    }

    fun popCurrentScreen(destinationRoute: String? = null) {
        navHostController?.popBackStack()
    }

    fun addOnScreenPushListener(scope: CoroutineScope, action: (String) -> Unit) {
        routerBackStackFlow.onEach {
            action.invoke(it)
        }.launchIn(scope)
    }

    fun addOnScreenPopListener(scope: CoroutineScope, action: (String?) -> Unit) {
        routerPopFlow.onEach {
            action.invoke(it)
        }.launchIn(scope)
    }

    fun setGlobalNavController(controller: NavHostController) {
        navHostController = controller
    }

    fun disposeGlobalNavController() {
        navHostController = null
    }
}