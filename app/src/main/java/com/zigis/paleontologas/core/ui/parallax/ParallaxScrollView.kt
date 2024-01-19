package com.zigis.paleontologas.core.ui.parallax

import android.content.Context
import android.content.res.TypedArray
import android.os.SystemClock
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.ui.parallax.base.InternalScrollView
import com.zigis.paleontologas.core.ui.parallax.base.ParallaxBase
import com.zigis.paleontologas.core.ui.parallax.interfaces.OnScrollViewChangeListener
import kotlin.math.abs

class ParallaxScrollView constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ParallaxBase<InternalScrollView>(context, attrs) {

    private lateinit var headerContainer: FrameLayout
    private lateinit var rootContainer: LinearLayout
    private var contentContainer: View? = null

    private var headerHeight = 0
    private val scalingRunnable: ScalingRunnable

    init {
        scalingRunnable = ScalingRunnable()
        pullRootView.setOnScrollViewChangedListener(object :
            OnScrollViewChangeListener {
            override fun onInternalScrollChanged(left: Int, top: Int, oldLeft: Int, oldTop: Int) {
                if (isPullToZoomEnabled && isParallax) {
                    val offset =
                        headerHeight - headerContainer.bottom + pullRootView.scrollY.toFloat()
                    if (offset > 0f && offset < headerHeight) {
                        val i = (0.65 * offset).toInt()
                        headerContainer.scrollTo(0, -i)
                    } else if (headerContainer.scrollY != 0) {
                        headerContainer.scrollTo(0, 0)
                    }
                }
            }
        })
    }

    override fun onLayout(paramBoolean: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        super.onLayout(paramBoolean, p1, p2, p3, p4)
        if (headerHeight == 0 && zoomView != null) {
            headerHeight = headerContainer.height
        }
    }

    override fun handleStyledAttributes(attributes: TypedArray) {
        rootContainer = LinearLayout(context).apply {
            orientation = VERTICAL
        }

        headerContainer = FrameLayout(context).apply {
            zoomView?.let { addView(it) }
            headerView?.let { addView(it) }
            rootContainer.addView(this)
        }

        val contentViewResId =
            attributes.getResourceId(R.styleable.ParallaxScrollView_contentView, 0)
        if (contentViewResId > 0) {
            contentContainer = LayoutInflater.from(context).inflate(contentViewResId, null, false)
            rootContainer.addView(contentContainer)
        }

        rootContainer.clipChildren = false
        headerContainer.clipChildren = false

        pullRootView.addView(rootContainer)
    }

    override fun createRootView(context: Context, attrs: AttributeSet?): InternalScrollView {
        return InternalScrollView(
            context,
            attrs
        )
    }

    override fun pullHeaderToZoom(newScrollValue: Int) {
        if (!scalingRunnable.isFinished) {
            scalingRunnable.abortAnimation()
        }
        val localLayoutParams = headerContainer.layoutParams
        localLayoutParams.height = abs(newScrollValue) + headerHeight
        headerContainer.layoutParams = localLayoutParams
    }

    override fun updateHeaderView() {
        headerContainer.apply {
            removeAllViews()
            zoomView?.let { addView(it) }
            headerView?.let { addView(it) }
        }
    }

    override fun smoothScrollToTop() {
        scalingRunnable.startAnimation()
    }

    override fun isReadyForPullStart(): Boolean {
        return pullRootView.scrollY == 0
    }

    fun setScrollContentView(contentView: View) {
        if (contentContainer != null) {
            rootContainer.removeView(contentContainer)
        }
        contentContainer = contentView
        rootContainer.addView(contentContainer)
    }

    internal inner class ScalingRunnable : Runnable {

        private var duration = 0L
        private var scale = 0f
        private var startTime = 0L
        var isFinished = true

        fun abortAnimation() {
            isFinished = true
        }

        override fun run() {
            if (zoomView != null) {
                val localLayoutParams: ViewGroup.LayoutParams
                if (!isFinished && scale > 1.0) {
                    val factor1 =
                        (SystemClock.currentThreadTimeMillis().toFloat() - startTime.toFloat()) / duration.toFloat()
                    val factor2 =
                        scale - (scale - 1.0f) * animationInterpolator.getInterpolation(factor1)
                    localLayoutParams = headerContainer.layoutParams
                    if (factor2 > 1.0f) {
                        localLayoutParams.height = (factor2 * headerHeight).toInt()
                        headerContainer.layoutParams = localLayoutParams
                        post(this)
                        return
                    }
                    isFinished = true
                }
            }
        }

        fun startAnimation() = zoomView?.let {
            startTime = SystemClock.currentThreadTimeMillis()
            duration = 200L
            scale = headerContainer.bottom.toFloat() / headerHeight
            isFinished = false
            post(this)
        }
    }

    companion object {
        private val animationInterpolator = Interpolator { paramAnonymousFloat: Float ->
            val constant = paramAnonymousFloat - 1.0f
            1.0f + constant * (constant * (constant * (constant * constant)))
        }
    }
}