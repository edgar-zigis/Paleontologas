package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun PlayerAvatar(
    player: QuizPlayer,
    modifier: Modifier = Modifier
) {
    val borderColor = Color(red = 0.6f, green = 0.4f, blue = 0.2f)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(bottom = 10.dp)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .border(width = 2.dp, color = borderColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.avatar,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Text(
                text = player.country,
                fontSize = 20.sp,
                modifier = Modifier.offset(y = 10.dp),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.2f),
                        offset = Offset(0f, 1f),
                        blurRadius = 2f
                    )
                )
            )
        }
    }
}
