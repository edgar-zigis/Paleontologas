package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.extensions.android.ContextWrapper
import com.zigis.paleontologas.core.extensions.getCurrentLocale
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : ComponentActivity(), CoroutineScope {

    private val applicationLocaleManager: ApplicationLocaleManager by inject()

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationLocaleManager.setInitialLocaleIfNeeded(
            phoneLocale = resources.configuration.getCurrentLocale()
        )
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            setAppLocaleLegacy()
        }
        job = Job()
        addLogs()
    }

    override fun onStart() {
        super.onStart()
        addLogs()
    }

    public override fun onResume() {
        super.onResume()
        addLogs()
    }

    public override fun onPause() {
        super.onPause()
        addLogs()
    }

    override fun onStop() {
        super.onStop()
        addLogs()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        addLogs()
    }

    override fun attachBaseContext(newBase: Context?) {
        val currentLocale = applicationLocaleManager.getCurrentLocale()
        if (newBase != null && currentLocale != null) {
            val context = ContextWrapper.wrap(newBase, currentLocale)
            super.attachBaseContext(context)
        } else {
            super.attachBaseContext(newBase)
        }
    }

    @Suppress("deprecation")
    private fun setAppLocaleLegacy() {
        val newLocale = applicationLocaleManager.getCurrentLocale() ?: return
        Locale.setDefault(newLocale)
        Configuration(resources.configuration).apply {
            locale = newLocale
            resources.updateConfiguration(this, resources.displayMetrics)
        }
    }

    private fun addLogs() {
        val methodName = Thread.currentThread().stackTrace[3].methodName
        FirebaseCrashlytics.getInstance().log(this.javaClass.simpleName + ": " + methodName)
    }
}