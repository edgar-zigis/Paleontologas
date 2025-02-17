package com.zigis.paleontologas.core.routers

import androidx.navigation.NavHostController
import java.io.Serializable

class GlobalRouter {

    private var navHostController: NavHostController? = null

    fun <T : Serializable> pushScreen(route: T) {
        navHostController?.navigate(route)
    }

    fun pushScreen(route: String) {
        navHostController?.navigate(route)
    }

    fun popCurrentScreen() {
        navHostController?.popBackStack()
    }

    fun setGlobalNavController(controller: NavHostController) {
        navHostController = controller
    }
}