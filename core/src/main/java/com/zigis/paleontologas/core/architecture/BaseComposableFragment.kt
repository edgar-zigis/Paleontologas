package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.databinding.FragmentComposeBinding
import com.zigis.paleontologas.core.interfaces.Navigable
import com.zigis.paleontologas.core.providers.SavedStateProvider

abstract class BaseComposableFragment : Fragment(), Navigable {

    private val savable = Bundle()
    private var binding: FragmentComposeBinding? = null

    protected fun <T> savedState() = SavedStateProvider.Nullable<T>(savable)

    abstract fun onCreateView(view: ComposeView)

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
    ): View? {
        binding = FragmentComposeBinding.inflate(inflater, container, false)
        binding?.rootView?.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            onCreateView(this)
        }
        return binding?.root
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
        binding = null
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