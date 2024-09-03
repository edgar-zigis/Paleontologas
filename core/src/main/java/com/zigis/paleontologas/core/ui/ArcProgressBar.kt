package com.zigis.paleontologas.core.ui

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.abs

@Composable
fun ArcProgressbar(
    currentValue: Float = 0f,
    startAngle: Float = 130f,
    sweepAngle: Float = 150f,
    label: String = "",
    unit: String = "",
    thickness: Dp = 8.dp,
    foregroundArcColor: Color = Color.LightGray,
    backgroundArcColor: Color = Color.Blue,
    labelTextStyle: TextStyle = TextStyle(
        fontSize = 20.sp,
        color = Color.Black
    ),
    valueTextStyle: TextStyle = TextStyle(
        fontSize = 100.sp,
        color = Color.Black
    ),
    unitTextStyle: TextStyle = TextStyle(
        fontSize = 25.sp,
        color = Color.Black
    )
) {
    val level by remember {
        mutableIntStateOf(currentValue.toInt() / 100)
    }

    val targetAnimatedValue = (abs(currentValue - (100 * level)) / 100) * (startAngle + sweepAngle)
    val progressAnimate = remember { Animatable(targetAnimatedValue) }

    val value = when {
        label.isBlank() -> (currentValue.toInt()).toString().plus(unit)
        else -> (currentValue.toInt()).toString()
    }

    val textMeasurer = rememberTextMeasurer()

    val labelTextLayoutResult = remember(label) {
        textMeasurer.measure(label, labelTextStyle)
    }
    val valueTextLayoutResult = remember(value) {
        textMeasurer.measure(value, valueTextStyle)
    }
    val unitTextLayoutResult = remember(unit) {
        textMeasurer.measure(unit, unitTextStyle)
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .padding(thickness / 2)
            .aspectRatio(1f),
        onDraw = {
            drawArc(
                color = foregroundArcColor,
                startAngle = startAngle,
                sweepAngle = startAngle + sweepAngle,
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Round),
                size = Size(size.width, size.height)
            )
            drawArc(
                color = backgroundArcColor,
                startAngle = startAngle,
                sweepAngle = progressAnimate.value,
                useCenter = false,
                style = Stroke(thickness.toPx(), cap = StrokeCap.Round),
                size = Size(size.width, size.height)
            )
            if (label.isNotBlank()) {
                drawText(
                    textMeasurer = textMeasurer,
                    text = label,
                    style = labelTextStyle,
                    topLeft = Offset(
                        x = center.x - labelTextLayoutResult.size.width / 2,
                        y = center.y - valueTextLayoutResult.size.height,
                    )
                )
                drawText(
                    textMeasurer = textMeasurer,
                    text = unit,
                    style = unitTextStyle,
                    topLeft = Offset(
                        x = center.x - unitTextLayoutResult.size.width / 2,
                        y = center.y + (valueTextLayoutResult.size.height / 2f),
                    )
                )
            }
            drawText(
                textMeasurer = textMeasurer,
                text = value,
                style = valueTextStyle,
                topLeft = Offset(
                    x = center.x - valueTextLayoutResult.size.width / 2,
                    y = center.y - valueTextLayoutResult.size.height / 2,
                )
            )
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ArcProgressBarPreview() {
    Column(
        modifier = Modifier
            .size(160.dp)
            .padding(8.dp)
    ) {
        ArcProgressbar(
            currentValue = 67f,
            label = "ANSWERED",
            unit = "%",
            thickness = 8.dp,
            labelTextStyle = TextStyle(
                fontSize = 10.sp,
                color = Color.Black
            ),
            valueTextStyle = TextStyle(
                fontSize = 36.sp,
                color = Color.Black
            ),
            unitTextStyle = TextStyle(
                fontSize = 12.sp,
                color = Color.Black
            )
        )
    }
}