package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun LeaderboardRow(
    player: QuizPlayer,
    showSeparator: Boolean,
    isHighlighted: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                if (isHighlighted) ApplicationTheme.colors.backgroundPrimary.copy(alpha = 0.1f)
                else ApplicationTheme.colors.contentBackground
            )
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "#${player.ranking}",
                modifier = Modifier.width(30.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium,
                    color = ApplicationTheme.colors.contentText.copy(alpha = 0.6f)
                )
            )

            Text(
                text = player.name,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium,
                    color = ApplicationTheme.colors.contentText
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "${player.points} XP",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium,
                    color = ApplicationTheme.colors.contentText.copy(alpha = 0.7f)
                )
            )

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = Color.Gray.copy(alpha = 0.2f),
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = player.country,
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = ThemeFonts.Gilroy,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }

        if (showSeparator) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(ApplicationTheme.colors.contentText)
                )
            }
        }
    }
}
