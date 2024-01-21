package com.zigis.paleontologas.core.architecture.v2

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.zigis.paleontologas.core.architecture.v2.interfaces.IState
import com.zigis.paleontologas.core.architecture.v2.interfaces.IView

abstract class BaseView<S : IState>(val context: Context) : IView<S> {

    protected abstract var binding: ViewBinding?
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    protected fun getString(@StringRes resId: Int): String = context.getString(resId)

    protected fun requireBinding(): ViewBinding {
        return binding ?: throw IllegalStateException("Binding is not set.")
    }

    open fun onAttached() {}

    fun onDestroyView() {
        binding = null
    }
}