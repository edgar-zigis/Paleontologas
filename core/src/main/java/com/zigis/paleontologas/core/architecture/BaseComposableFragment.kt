package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.fragment.app.Fragment
import androidx.fragment.compose.content
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.interfaces.Navigable
import com.zigis.paleontologas.core.providers.SavedStateProvider

abstract class BaseComposableFragment : Fragment(), Navigable {

    private val savable = Bundle()

    protected fun <T> savedState() = SavedStateProvider.Nullable<T>(savable)

    @Composable
    abstract fun ViewContentComposition()

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            savable.putAll(savedInstanceState.getBundle("_state"))
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = content {
        ViewContentComposition()
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onAttached() { }

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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        onAttached()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle("_state", savable)
        super.onSaveInstanceState(outState)
    }

    private fun addLogs() {
        val methodName = Thread.currentThread().stackTrace[3].methodName
        FirebaseCrashlytics.getInstance().log(this.javaClass.simpleName + ": " + methodName)
    }
}