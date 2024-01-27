package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.zigis.paleontologas.core.architecture.interfaces.IIntent
import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.launchOnRepeat
import com.zigis.paleontologas.core.interfaces.Navigable
import com.zigis.paleontologas.core.providers.SavedStateProvider

abstract class BaseFragment<S : IState, I : IIntent, M : BaseViewModel<S, I>> : Fragment(), Navigable {

    protected abstract val viewModel: M
    private val savable = Bundle()
    private var contentView: IView<S>? = null

    protected fun <T> savedState() = SavedStateProvider.Nullable<T>(savable)

    abstract fun onCreateView(context: Context): IView<S>

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
        return onCreateView(inflater.context).also {
            contentView = it
        } as View
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchOnRepeat {
            viewModel.state.collect {
                contentView?.render(it)
            }
        }
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
        (contentView as? BaseView<*, *>)?.onDestroyView()
        contentView = null
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