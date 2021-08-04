package com.zigis.paleontologas.application.ui.parallax.base

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.*
import android.view.MotionEvent.*
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.ui.parallax.interfaces.Parallax
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

abstract class ParallaxBase<T : View> constructor(
    context: Context,
    attributes: AttributeSet? = null
) : LinearLayout(context, attributes),
    Parallax<T> {

    override lateinit var pullRootView: T

    override var zoomView: View? = null
        set(value) {
            field = value
            updateHeaderView()
        }

    override var headerView: View? = null
        set(value) {
            field = value
            updateHeaderView()
        }

    override var isPullToZoomEnabled: Boolean = true
    override var isZooming: Boolean = false
    override var isParallax: Boolean = true

    private var screenHeight = 0
    private var screenWidth = 0

    private var touchSlop = 0
    private var isBeingDragged = false
    private var lastMotionY = 0f
    private var lastMotionX = 0f
    private var initialMotionY = 0f
    private var initialMotionX = 0f

    init {
        init(context, attributes)
    }

    protected abstract fun updateHeaderView()
    protected abstract fun pullHeaderToZoom(newScrollValue: Int)
    protected abstract fun createRootView(context: Context, attrs: AttributeSet?): T
    protected abstract fun smoothScrollToTop()
    protected abstract fun isReadyForPullStart(): Boolean

    @Suppress("Deprecation")
    private fun init(context: Context, attributes: AttributeSet?) {
        gravity = Gravity.CENTER

        val config = ViewConfiguration.get(context)
        touchSlop = config.scaledTouchSlop

        val localDisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)

        screenHeight = localDisplayMetrics.heightPixels
        screenWidth = localDisplayMetrics.widthPixels

        pullRootView = createRootView(context, attributes)

        if (attributes != null) {
            val a = context.obtainStyledAttributes(attributes, R.styleable.ParallaxScrollView)

            val zoomViewResId = a.getResourceId(R.styleable.ParallaxScrollView_zoomView, 0)
            if (zoomViewResId > 0) {
                zoomView = LayoutInflater.from(context).inflate(zoomViewResId, null, false)
            }

            val headerViewResId = a.getResourceId(R.styleable.ParallaxScrollView_headerView, 0)
            if (headerViewResId > 0) {
                headerView = LayoutInflater.from(context).inflate(headerViewResId, null, false)
            }

            isParallax = a.getBoolean(R.styleable.ParallaxScrollView_isHeaderParallax, true)

            handleStyledAttributes(a)
            a.recycle()
        }

        addView(pullRootView, MATCH_PARENT, MATCH_PARENT)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (!isPullToZoomEnabled) {
            return false
        }

        val action = event.action
        if (action == ACTION_CANCEL || action == ACTION_UP) {
            isBeingDragged = false
            return false
        }
        if (action != ACTION_DOWN && isBeingDragged) return true

        when (action) {
            ACTION_MOVE -> {
                if (isReadyForPullStart()) {
                    val diff = event.y - lastMotionY
                    val oppositeDiff = event.x - lastMotionX
                    val absDiff = abs(diff)
                    if (absDiff > touchSlop && absDiff > abs(oppositeDiff)) {
                        if (diff >= 1f) {
                            lastMotionY = event.y
                            lastMotionX = event.x
                            isBeingDragged = true
                        }
                    }
                }
            }
            ACTION_DOWN -> {
                if (isReadyForPullStart()) {
                    initialMotionY = event.y
                    lastMotionY = initialMotionY
                    initialMotionX = event.x
                    lastMotionX = initialMotionX
                    isBeingDragged = false
                }
            }
        }
        return isBeingDragged
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isPullToZoomEnabled) {
            return false
        }

        if (event.action == ACTION_DOWN && event.edgeFlags != 0) return false

        when (event.action) {
            ACTION_MOVE -> {
                if (isBeingDragged) {
                    lastMotionY = event.y
                    lastMotionX = event.x
                    pullEvent()
                    isZooming = true
                    return true
                }
            }
            ACTION_DOWN -> {
                if (isReadyForPullStart()) {
                    initialMotionY = event.y
                    lastMotionY = initialMotionY
                    initialMotionX = event.x
                    lastMotionX = initialMotionX
                    return true
                }
            }
            ACTION_CANCEL, ACTION_UP -> {
                if (isBeingDragged) {
                    isBeingDragged = false
                    if (isZooming) {
                        smoothScrollToTop()
                        isZooming = false
                        return true
                    }
                    return true
                }
            }
        }
        return false
    }

    private fun pullEvent() {
        val newScrollValue = (min(initialMotionY - lastMotionY, 0f) / FRICTION).roundToInt()
        pullHeaderToZoom(newScrollValue)
    }

    companion object {
        private const val FRICTION = 2.0f
    }
}