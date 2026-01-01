package com.zigis.paleontologas.features.quiz.stories.categoryselection.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.quiz.entities.QuizCategory

@Composable
fun QuizCategoryCardView(
    category: QuizCategory,
    onTap: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.98f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = "cardScale"
    )

    Column(
        modifier = modifier
            .scale(scale)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(8.dp),
                clip = false
            )
            .clip(RoundedCornerShape(8.dp))
            .background(ApplicationTheme.colors.contentBackground)
            .clickable(
                interactionSource = interactionSource,
                onClick = onTap
            )
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(category.title).uppercase(),
                style = ApplicationTheme.typography.headline3,
                color = ApplicationTheme.colors.contentText,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = category.answeredCount.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.Success
                )
                Text(
                    text = "/",
                    fontSize = 18.sp,
                    color = Color.Gray
                )
                Text(
                    text = category.totalCount.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = ThemeColors.Success
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.questions_answered),
                style = ApplicationTheme.typography.caption1,
                color = Color.Gray,
                modifier = Modifier.offset(y = (-8).dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = category.formattedProgress,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = ApplicationTheme.colors.contentText
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        PodiumProgressBar(
            progress = category.progress.toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
    }
}

@Composable
private fun PodiumProgressBar(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val clamped = progress.coerceIn(0f, 1f)

    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = 0.2f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(clamped)
                .clip(CircleShape)
                .background(ThemeColors.Success)
        )
    }
}