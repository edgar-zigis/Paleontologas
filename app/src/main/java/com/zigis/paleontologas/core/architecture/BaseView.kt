package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.zigis.paleontologas.core.architecture.interfaces.IState
import com.zigis.paleontologas.core.architecture.interfaces.IView

abstract class BaseView<S : IState, V : ViewBinding>(context: Context) : FrameLayout(context),
    IView<S> {

    protected abstract var binding: V?
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    protected fun getString(@StringRes resId: Int): String = context.getString(resId)

    protected fun requireBinding(): V {
        return binding ?: throw IllegalStateException("Binding is not set.")
    }

    open fun onAttached() {}

    fun onDestroyView() {
        binding = null
    }
}