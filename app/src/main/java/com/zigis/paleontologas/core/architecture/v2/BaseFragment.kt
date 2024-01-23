package com.zigis.paleontologas.core.architecture.v2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.evernote.android.state.StateSaver
import com.zigis.paleontologas.PaleoApplication
import com.zigis.paleontologas.core.architecture.v2.interfaces.IIntent
import com.zigis.paleontologas.core.architecture.v2.interfaces.IState
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView
import com.zigis.paleontologas.core.extensions.launchOnRepeat
import com.zigis.paleontologas.core.interfaces.Navigable

abstract class BaseFragment<S : IState, I : IIntent, M : BaseViewModel<S, I>> : Fragment(), Navigable {

    protected abstract val viewModel: M
    private var contentView: IView<S>? = null

    abstract fun onCreateView(context: Context): IView<S>

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
            viewModel.state.collect { contentView?.render(it) }
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
        (contentView as? BaseView<*, *>)?.onDestroyView()
        contentView = null
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
        //FirebaseCrashlytics.getInstance().log(this.javaClass.simpleName + ": " + methodName)
    }
}