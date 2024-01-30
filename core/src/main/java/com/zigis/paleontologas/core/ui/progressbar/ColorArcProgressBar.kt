package com.zigis.paleontologas.core.ui.progressbar

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.core.content.res.ResourcesCompat
import com.zigis.paleontologas.core.R

@Suppress("Deprecation")
class ColorArcProgressBar : View {

    private val screenWidth: Int
        get() {
            val windowManager =
                context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            return displayMetrics.widthPixels
        }

    private lateinit var allArcPaint: Paint
    private lateinit var progressPaint: Paint
    private lateinit var vTextPaint: Paint
    private lateinit var hintPaint: Paint
    private lateinit var degreePaint: Paint
    private lateinit var curSpeedPaint: Paint
    private lateinit var bgRect: RectF

    private lateinit var mDrawFilter: PaintFlagsDrawFilter
    private lateinit var sweepGradient: SweepGradient
    private lateinit var rotateMatrix: Matrix
    private lateinit var progressAnimator: ValueAnimator

    private var diameter = 500
    private var centerX = 0f
    private var centerY = 0f

    private val startAngle = 135f
    private var sweepAngle = 270f
    private var currentAngle = 0f
    private var lastAngle = 0f

    private var colors = intArrayOf(
        Color.GREEN,
        Color.YELLOW,
        Color.RED,
        Color.RED
    )

    private var maxValues = 100f
    private var curValues = 0f
    private var bgArcWidth = dipToPx(2f).toFloat()
    private var progressWidth = dipToPx(10f).toFloat()
    private var textSize = dipToPx(60f).toFloat()
    private var hintSize = dipToPx(15f).toFloat()
    private val curSpeedSize = dipToPx(13f).toFloat()

    private val longdegree = dipToPx(13f).toFloat()
    private val shortdegree = dipToPx(5f).toFloat()
    private val degreeProgressDistance = dipToPx(2f)

    private val hintColor = "#676767"
    private val longDegreeColor = "#111111"
    private val shortDegreeColor = "#111111"
    private val bgArcColor = "#BDBDBD"

    private var titleString: String? = null
    private var hintString: String? = null

    private var isNeedTitle = false
    private var isNeedUnit = false
    private var isNeedDial = false
    private var isNeedContent = false
    private var k = 0f

    constructor(context: Context?) : super(context, null) {
        initView()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs, 0) {
        initCofig(context, attrs)
        initView()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initCofig(context, attrs)
        initView()
    }

    private fun initCofig(
        context: Context,
        attrs: AttributeSet?
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ColorArcProgressBar)
        val color1 =
            a.getColor(R.styleable.ColorArcProgressBar_front_color1, Color.GREEN)
        val color2 = a.getColor(R.styleable.ColorArcProgressBar_front_color2, color1)
        val color3 = a.getColor(R.styleable.ColorArcProgressBar_front_color3, color1)
        colors = intArrayOf(color1, color2, color3, color3)
        sweepAngle = a.getInteger(R.styleable.ColorArcProgressBar_total_engle, 270).toFloat()
        bgArcWidth =
            a.getDimension(R.styleable.ColorArcProgressBar_back_width, dipToPx(10f).toFloat())
        progressWidth =
            a.getDimension(R.styleable.ColorArcProgressBar_front_width, dipToPx(10f).toFloat())
        isNeedTitle = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_title, false)
        isNeedContent = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_content, false)
        isNeedUnit = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_unit, false)
        isNeedDial = a.getBoolean(R.styleable.ColorArcProgressBar_is_need_dial, false)
        hintString = a.getString(R.styleable.ColorArcProgressBar_string_unit)
        titleString = a.getString(R.styleable.ColorArcProgressBar_string_title)
        curValues = a.getFloat(R.styleable.ColorArcProgressBar_current_value, 50f)
        maxValues = a.getFloat(R.styleable.ColorArcProgressBar_max_value, 100f)
        setCurrentValues(curValues)
        setMaxValues(maxValues)
        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width =
            (2 * longdegree + progressWidth + diameter + 2 * degreeProgressDistance).toInt()
        val height =
            (2 * longdegree + progressWidth + diameter + 2 * degreeProgressDistance).toInt()
        setMeasuredDimension(width, height)
    }

    private fun initView() {
        diameter = 3 * screenWidth / 6

        bgRect = RectF().apply {
            top = longdegree + progressWidth / 2 + degreeProgressDistance
            left = longdegree + progressWidth / 2 + degreeProgressDistance
            right = diameter + (longdegree + progressWidth / 2 + degreeProgressDistance)
            bottom = diameter + (longdegree + progressWidth / 2 + degreeProgressDistance)
        }

        centerX = (2 * longdegree + progressWidth + diameter + 2 * degreeProgressDistance) / 2
        centerY = (2 * longdegree + progressWidth + diameter + 2 * degreeProgressDistance) / 2

        degreePaint = Paint().apply {
            color = Color.parseColor(longDegreeColor)
        }
        allArcPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeWidth = bgArcWidth
            color = Color.parseColor(bgArcColor)
            strokeCap = Paint.Cap.ROUND
        }
        progressPaint = Paint().apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = progressWidth
            color = Color.GREEN
        }
        vTextPaint = Paint().apply {
            textSize = this@ColorArcProgressBar.textSize
            color = Color.BLACK
            textAlign = Paint.Align.CENTER
            typeface = ResourcesCompat.getFont(context, R.font.gentona_book)
        }
        hintPaint = Paint().apply {
            textSize = hintSize
            color = Color.parseColor(hintColor)
            textAlign = Paint.Align.CENTER
            typeface = ResourcesCompat.getFont(context, R.font.gentona_book)
        }
        curSpeedPaint = Paint().apply {
            textSize = curSpeedSize
            color = Color.parseColor(hintColor)
            textAlign = Paint.Align.CENTER
            typeface = ResourcesCompat.getFont(context, R.font.gentona_book)
        }
        mDrawFilter = PaintFlagsDrawFilter(
            0,
            Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG
        )
        sweepGradient = SweepGradient(centerX, centerY, colors, null)
        rotateMatrix = Matrix()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawFilter = mDrawFilter
        if (isNeedDial) {
            for (i in 0..39) {
                if (i in 16..24) {
                    canvas.rotate(9f, centerX, centerY)
                    continue
                }
                if (i % 5 == 0) {
                    degreePaint.strokeWidth = dipToPx(2f).toFloat()
                    degreePaint.color = Color.parseColor(longDegreeColor)
                    canvas.drawLine(
                        centerX,
                        centerY - diameter / 2 - progressWidth / 2 - degreeProgressDistance,
                        centerX,
                        centerY - diameter / 2 - progressWidth / 2 - degreeProgressDistance - longdegree,
                        degreePaint
                    )
                } else {
                    degreePaint.strokeWidth = dipToPx(1.4f).toFloat()
                    degreePaint.color = Color.parseColor(shortDegreeColor)
                    canvas.drawLine(
                        centerX,
                        centerY - diameter / 2 - progressWidth / 2 - degreeProgressDistance - (longdegree - shortdegree) / 2,
                        centerX,
                        centerY - diameter / 2 - progressWidth / 2 - degreeProgressDistance - (longdegree - shortdegree) / 2 - shortdegree,
                        degreePaint
                    )
                }
                canvas.rotate(9f, centerX, centerY)
            }
        }
        canvas.drawArc(bgRect, startAngle, sweepAngle, false, allArcPaint)
        rotateMatrix.setRotate(130f, centerX, centerY)
        sweepGradient.setLocalMatrix(rotateMatrix)
        progressPaint.shader = sweepGradient
        canvas.drawArc(bgRect, startAngle, currentAngle, false, progressPaint)
        if (isNeedContent) {
            canvas.drawText(
                String.format("%.0f", curValues),
                centerX,
                centerY + textSize / 3,
                vTextPaint
            )
        }
        if (isNeedUnit) {
            canvas.drawText(hintString!!, centerX, centerY + 2 * textSize / 3, hintPaint)
        }
        if (isNeedTitle) {
            canvas.drawText(titleString!!, centerX, centerY - 2 * textSize / 3, curSpeedPaint)
        }
        invalidate()
    }

    private fun setMaxValues(maxValues: Float) {
        this.maxValues = maxValues
        k = sweepAngle / maxValues
    }

    fun setCurrentValues(currentValues: Float) {
        var values = currentValues
        if (values > maxValues) {
            values = maxValues
        }
        if (values < 0) {
            values = 0f
        }
        curValues = values
        lastAngle = currentAngle
        setAnimation(lastAngle, values * k)
    }

    private fun setAnimation(last: Float, current: Float) {
        progressAnimator = ValueAnimator.ofFloat(last, current).apply {
            duration = 1000L
            setTarget(currentAngle)
            addUpdateListener { animation ->
                currentAngle = animation.animatedValue as Float
                curValues = currentAngle / k
            }
            start()
        }
    }

    private fun dipToPx(dip: Float): Int {
        val density = context.resources.displayMetrics.density
        return (dip * density + 0.5f * if (dip >= 0) 1 else -1).toInt()
    }
}