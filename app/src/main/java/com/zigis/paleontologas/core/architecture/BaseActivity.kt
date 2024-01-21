package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View.LAYOUT_DIRECTION_RTL
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.PaleoApplication
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.extensions.android.ContextWrapper
import com.zigis.paleontologas.core.extensions.getCurrentLocale
import com.zigis.paleontologas.core.interfaces.Navigable
import com.zigis.paleontologas.core.managers.ApplicationLocaleManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.android.ext.android.inject
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.math.min

abstract class BaseActivity : AppCompatActivity(), CoroutineScope {

    private val applicationLocaleManager: ApplicationLocaleManager by inject()

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val backStackChangeListener = FragmentManager.OnBackStackChangedListener {
        with(supportFragmentManager) {
            val index = min(backStackEntryCount - 1, fragments.size - 1)
            (fragments[index] as? Navigable)?.onAttached()
        }
    }

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
        supportFragmentManager.addOnBackStackChangedListener(backStackChangeListener)
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
        supportFragmentManager.removeOnBackStackChangedListener(backStackChangeListener)
    }

    override fun onBackPressed() {
        with(supportFragmentManager) {
            if (backStackEntryCount == 1) {
                finish()
            } else {
                fragments.firstOrNull { it.isVisible }?.let { fragment ->
                    if (fragment !is Navigable || !fragment.onBackPressed()) {
                        super.onBackPressed()
                    }
                } ?: super.onBackPressed()
            }
        }
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

    protected fun pushFragment(fragment: Fragment, container: Int, animated: Boolean = true) {
        with(supportFragmentManager.beginTransaction()) {
            if (animated) {
                if (resources.configuration.layoutDirection == LAYOUT_DIRECTION_RTL) {
                    setCustomAnimations(
                        R.anim.fragment_transition_pop_enter,
                        R.anim.fragment_transition_pop_exit,
                        R.anim.fragment_transition_enter,
                        R.anim.fragment_transition_exit
                    )
                } else {
                    setCustomAnimations(
                        R.anim.fragment_transition_enter,
                        R.anim.fragment_transition_exit,
                        R.anim.fragment_transition_pop_enter,
                        R.anim.fragment_transition_pop_exit
                    )
                }
            }
            add(container, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
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
        if (PaleoApplication.disableCrashLytics) return
        val methodName = Thread.currentThread().stackTrace[3].methodName
        FirebaseCrashlytics.getInstance().log(this.javaClass.simpleName + ": " + methodName)
    }
}