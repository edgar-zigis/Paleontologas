package com.zigis.paleontologas.core.ui.parallax.base

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import com.zigis.paleontologas.core.ui.parallax.interfaces.OnScrollViewChangeListener

class InternalScrollView constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ScrollView(context, attrs) {

    private var onScrollViewChangedListener: OnScrollViewChangeListener? = null

    fun setOnScrollViewChangedListener(onScrollViewChangedListener: OnScrollViewChangeListener?) {
        this.onScrollViewChangedListener = onScrollViewChangedListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onScrollViewChangedListener?.onInternalScrollChanged(l, t, oldl, oldt)
    }
}