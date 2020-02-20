package com.zigis.paleontologas.application.android

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crashlytics.android.Crashlytics
import com.evernote.android.state.StateSaver
import com.zigis.paleontologas.PaleoApplication
import com.zigis.paleontologas.application.interfaces.Navigable
import com.zigis.paleontologas.application.routers.GlobalRouter
import org.koin.android.ext.android.inject

abstract class BaseFragment<V : View> : Fragment(), Navigable {

    protected val globalRouter: GlobalRouter by inject()
    protected lateinit var contentView: V

    abstract fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        addLogs()
        return onCreateView(inflater, container).also {
            contentView = it
        }
    }

    override fun onAttached() { }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addLogs()
    }

    override fun onDetach() {
        super.onDetach()
        addLogs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        addLogs()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        StateSaver.saveInstanceState(this, outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        StateSaver.restoreInstanceState(this, savedInstanceState)
        onAttached()
    }

    private fun addLogs() {
        if (PaleoApplication.disableCrashLytics) return
        val methodName = Thread.currentThread().stackTrace[3].methodName
        Crashlytics.log(this.javaClass.simpleName + ": " + methodName)
    }
}