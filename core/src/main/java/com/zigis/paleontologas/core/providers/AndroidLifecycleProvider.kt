package com.zigis.paleontologas.core.providers

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle

class AndroidLifecycleProvider(
    private val applicationContext: Context
) {
    val activityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            setActivity(activity)
        }
        override fun onActivityStarted(activity: Activity) { setActivity(activity) }
        override fun onActivityResumed(activity: Activity) { setActivity(activity) }
        override fun onActivityPaused(activity: Activity) { clearActivity(activity) }
        override fun onActivityStopped(activity: Activity) { clearActivity(activity) }
        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) { }
        override fun onActivityDestroyed(activity: Activity) { clearActivity(activity) }
    }

    private var currentActivity: Activity? = null

    fun getActivity(): Activity? {
        return currentActivity
    }

    fun getActiveContext(): Context {
        return getActivity() ?: applicationContext
    }

    fun getApplicationContext(): Context {
        return applicationContext
    }

    private fun setActivity(activity: Activity) {
        if (currentActivity != activity) {
            currentActivity = activity
        }
    }

    private fun clearActivity(currentActivity: Activity) {
        if (this.currentActivity == currentActivity) {
            this.currentActivity = null
        }
    }
}