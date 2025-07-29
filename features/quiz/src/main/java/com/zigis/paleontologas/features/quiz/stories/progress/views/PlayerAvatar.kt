package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun PlayerAvatar(
    player: QuizPlayer,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(color = Color.Gray.copy(alpha = 0.2f), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = player.avatar,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = player.country,
                modifier = Modifier.offset(y = 49.dp),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        Text(
            text = player.name,
            modifier = Modifier
                .padding(top = 14.dp, start = 2.dp, end = 2.dp),
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Medium
            ),
            color = ApplicationTheme.colors.contentText,
            maxLines = 2,
            textAlign = TextAlign.Center
        )

        Text(
            text = "${player.points} XP",
            modifier = Modifier
                .padding(top = 2.dp, bottom = 4.dp),
            style = ApplicationTheme.typography.caption2,
            color = ApplicationTheme.colors.contentText.copy(alpha = 0.7f)
        )
    }
}
