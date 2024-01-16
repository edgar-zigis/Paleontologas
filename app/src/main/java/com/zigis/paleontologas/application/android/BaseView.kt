package com.zigis.paleontologas.application.android

import android.content.Context
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewBaseBinding

abstract class BaseView<V: ViewBinding>(context: Context, val viewBinding: V) : RelativeLayout(context) {

    protected abstract val titleTextResId: Int
    protected val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var onBack: (() -> Unit)? = null

    private val baseBinding = ViewBaseBinding.inflate(layoutInflater, this)

    init {
        isClickable = true
        isFocusable = true
        background = ContextCompat.getDrawable(context, R.drawable.bg_launcher)

        addView(baseBinding.root)
        baseBinding.viewContainer.addView(viewBinding.root)

        baseBinding.title.text = context.getString(titleTextResId)
        baseBinding.backButton.setDebounceClickListener {
            onBack?.invoke()
        }

        initialize()
    }

    protected abstract fun initialize()
}