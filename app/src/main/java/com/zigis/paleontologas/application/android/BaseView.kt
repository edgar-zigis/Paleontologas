package com.zigis.paleontologas.application.android

import android.content.Context
import android.widget.RelativeLayout
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_base.view.*

abstract class BaseView(context: Context, layoutResId: Int) : RelativeLayout(context) {

    var onBack: (() -> Unit)? = null

    init {
        isClickable = true
        isFocusable = true
        background = context.getDrawable(R.drawable.bg_launcher)

        inflate(context, R.layout.view_base, this)
        inflate(context, layoutResId, viewContainer)

        backButton.setDebounceClickListener {
            onBack?.invoke()
        }

        initialize()
    }

    protected abstract fun initialize()
}