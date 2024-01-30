package com.zigis.paleontologas.core.ui.parallax

import android.content.Context
import android.util.AttributeSet
import android.widget.ScrollView
import com.zigis.paleontologas.core.ui.parallax.OnScrollViewChangeListener

class InternalScrollView(
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