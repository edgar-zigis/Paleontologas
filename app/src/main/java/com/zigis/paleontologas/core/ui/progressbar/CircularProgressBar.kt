package com.zigis.paleontologas.core.ui.progressbar

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.ui.progressbar.interfaces.ProgressAnimationListener
import kotlin.math.abs
import kotlin.math.min

class CircularProgressBar : ProgressBar {

    private var barTitle = ""
    private var subTitle = ""

    private var strokeWidth =
        STROKE_WIDTH

    private val circleBounds = RectF()

    private val progressColorPaint = Paint()
    private val backgroundColorPaint = Paint()
    private val titlePaint = Paint()
    private val subtitlePaint = Paint()

    private var hasShadow = true
    private var shadowColor = Color.BLACK

    var title: String
        get() = this.barTitle
        @Synchronized set(title) {
            this.barTitle = title
            invalidate()
        }

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)

        context.obtainStyledAttributes(attrs, R.styleable.CircularProgressBar, defStyle, 0).apply {
            hasShadow = getBoolean(R.styleable.CircularProgressBar_hasShadow, true)
            progressColorPaint.color = getColor(
                R.styleable.CircularProgressBar_progressColor,
                ContextCompat.getColor(context, R.color.white)
            )
            backgroundColorPaint.color = getColor(
                R.styleable.CircularProgressBar_backgroundColor,
                ContextCompat.getColor(context, R.color.circular_progress_default_background)
            )
            titlePaint.color = getColor(
                R.styleable.CircularProgressBar_titleColor,
                ContextCompat.getColor(context, R.color.white)
            )
            subtitlePaint.color = getColor(
                R.styleable.CircularProgressBar_subtitleColor,
                ContextCompat.getColor(context, R.color.white)
            )
            barTitle = getString(R.styleable.CircularProgressBar_title) ?: ""
            subTitle = getString(R.styleable.CircularProgressBar_subtitle) ?: ""
            strokeWidth = getInt(
                R.styleable.CircularProgressBar_strokeSize,
                STROKE_WIDTH
            )
            recycle()
        }

        with(progressColorPaint) {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = this@CircularProgressBar.strokeWidth.toFloat()
        }

        with(backgroundColorPaint) {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = this@CircularProgressBar.strokeWidth.toFloat()
        }

        with(titlePaint) {
            textSize = pxFromDp(context, 10f)
            style = Paint.Style.FILL
            isAntiAlias = true
            typeface = ResourcesCompat.getFont(context, R.font.gilroy_regular)
            setShadowLayer(0.1f, 0f, 1f, Color.GRAY)
        }

        with(subtitlePaint) {
            textSize = 11f
            style = Paint.Style.FILL
            isAntiAlias = true
            typeface = ResourcesCompat.getFont(context, R.font.gilroy_regular)
        }
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        canvas.drawArc(circleBounds, 0f, 360f, false, backgroundColorPaint)

        val scale = if (max > 0) progress.toFloat() / max * 360 else 0f
        if (hasShadow) {
            progressColorPaint.setShadowLayer(3f, 0f, 1f, shadowColor)
        }
        canvas.drawArc(circleBounds, 270f, scale, false, progressColorPaint)

        if (!TextUtils.isEmpty(this.barTitle)) {
            var xPos = (measuredWidth / 2 - titlePaint.measureText(this.barTitle) / 2).toInt()
            var yPos = measuredHeight / 2

            val titleHeight = abs(titlePaint.descent() + titlePaint.ascent())
            if (TextUtils.isEmpty(subTitle)) {
                yPos += (titleHeight / 2).toInt()
            }
            canvas.drawText(this.barTitle, xPos.toFloat(), yPos.toFloat(), titlePaint)

            yPos += titleHeight.toInt()
            xPos = (measuredWidth / 2 - subtitlePaint.measureText(subTitle) / 2).toInt()

            canvas.drawText(subTitle, xPos.toFloat(), yPos.toFloat(), subtitlePaint)
        }

        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val min = min(width, height)
        setMeasuredDimension(min + 2 * STROKE_WIDTH, min + 2 * STROKE_WIDTH)

        circleBounds.set(
            STROKE_WIDTH.toFloat(),
            STROKE_WIDTH.toFloat(),
            (min + STROKE_WIDTH).toFloat(),
            (min + STROKE_WIDTH).toFloat()
        )
    }

    @Synchronized
    override fun setProgress(progress: Int) {
        super.setProgress(progress)
        invalidate()
    }

    fun animateProgressTo(start: Int, end: Int, listener: ProgressAnimationListener? = null) {
        if (start != 0)
            progress = start

        val progressBarAnimator =
            ObjectAnimator.ofFloat(this, "animateProgress", start.toFloat(), end.toFloat())
        progressBarAnimator.duration = 1000
        progressBarAnimator.interpolator = AccelerateDecelerateInterpolator()

        progressBarAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                this@CircularProgressBar.progress = end
                listener?.onAnimationFinish()
            }

            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationStart(animation: Animator) {
                listener?.onAnimationStart()
            }
        })

        progressBarAnimator.addUpdateListener { animation ->
            val progress = (animation.animatedValue as Float).toInt()
            if (progress != this@CircularProgressBar.progress) {
                this@CircularProgressBar.progress = progress
                listener?.onAnimationProgress(progress)
            }
        }
        progressBarAnimator.start()
    }

    companion object {
        private const val STROKE_WIDTH = 20

        fun pxFromDp(context: Context, dp: Float): Float {
            return dp * context.resources.displayMetrics.density
        }
    }
}