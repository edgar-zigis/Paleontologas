package com.zigis.paleontologas.application.architecture

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding

abstract class BaseView(context: Context) : FrameLayout(context) {

    protected abstract val binding: ViewBinding
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    protected fun getString(@StringRes resId: Int): String = context.getString(resId)
}