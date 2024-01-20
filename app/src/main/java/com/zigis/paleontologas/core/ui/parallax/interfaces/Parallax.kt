package com.zigis.paleontologas.core.ui.parallax.interfaces

import android.content.res.TypedArray
import android.view.View

interface Parallax<T : View> {
    var pullRootView: T
    var zoomView: View?
    var headerView: View?
    var isPullToZoomEnabled: Boolean
    var isZooming: Boolean
    var isParallax: Boolean
    fun handleStyledAttributes(attributes: TypedArray)
}