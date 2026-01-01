package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun PodiumView(
    modifier: Modifier = Modifier,
    players: List<QuizPlayer>
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val totalPodiumWidth = min(maxWidth, 390.dp)
        val columnWidth = totalPodiumWidth / 3f

        Row(
            modifier = Modifier
                .width(totalPodiumWidth)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            PodiumColumn(
                player = players.getOrNull(1),
                rank = 2,
                podiumPainter = painterResource(id = R.drawable.podium_silver),
                width = columnWidth,
                imageOffset = 10.dp
            )
            PodiumColumn(
                player = players.getOrNull(0),
                rank = 1,
                podiumPainter = painterResource(id = R.drawable.podium_gold),
                width = columnWidth,
                isWinner = true,
                imageOffset = 20.dp,
                modifier = Modifier.zIndex(1f)
            )
            PodiumColumn(
                player = players.getOrNull(2),
                rank = 3,
                podiumPainter = painterResource(id = R.drawable.podium_bronze),
                width = columnWidth,
                imageOffset = 15.dp
            )
        }
    }
}

@Composable
private fun PodiumColumn(
    player: QuizPlayer?,
    rank: Int,
    podiumPainter: Painter,
    width: Dp,
    isWinner: Boolean = false,
    imageOffset: Dp = 0.dp,
    modifier: Modifier = Modifier
) {
    val avatarBottomOffset = when (rank) {
        2 -> 25.dp
        3 -> 35.dp
        else -> 15.dp
    }

    Column(
        modifier = modifier.width(width),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (player != null) {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .zIndex(2f)
                    .offset(y = avatarBottomOffset) // <-- sinks avatar down into podium
            ) {
                PlayerAvatar(
                    player = player,
                    modifier = Modifier.scale(if (isWinner) 1.2f else 1f)
                )

                if (isWinner) {
                    Text(
                        text = "👑",
                        fontSize = 30.sp,
                        modifier = Modifier.offset(y = (-30).dp)
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.size(50.dp))
        }

        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                painter = podiumPainter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.width(width)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(1.dp),
                modifier = Modifier
                    .padding(top = 32.dp + imageOffset, bottom = 8.dp)
                    .width(width * 0.9f)
            ) {
                Text(
                    text = "#$rank",
                    color = ApplicationTheme.colors.contentText,
                    style = TextStyle(
                        fontSize = 29.sp,
                        fontFamily = ThemeFonts.Gilroy,
                        fontWeight = FontWeight.Bold
                    )
                )

                if (player != null) {
                    Text(
                        text = player.name,
                        color = ApplicationTheme.colors.contentText,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = ThemeFonts.Gilroy,
                            fontWeight = FontWeight.Medium
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = "${player.points} XP",
                        color = ApplicationTheme.colors.contentText,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = ThemeFonts.Gilroy,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

