package com.zigis.paleontologas.features.main

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.zigis.paleontologas.core.architecture.BaseActivity
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.main.stories.main.MainScreen
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private val globalRouter: GlobalRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            ApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    globalRouter.setGlobalNavController(navController)
                    MainScreen(
                        navController = navController
                    )
                }
            }
        }
    }
}
