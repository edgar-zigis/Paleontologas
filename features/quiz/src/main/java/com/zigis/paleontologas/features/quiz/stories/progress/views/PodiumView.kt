package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun PodiumView(
    modifier: Modifier = Modifier,
    players: List<QuizPlayer>
) {
    BoxWithConstraints {
        val boxWithConstraintsScope = this

        Row(
            modifier = modifier,
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (players.size > 1) {
                    PlayerAvatar(
                        player = players[1],
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(boxWithConstraintsScope.maxWidth * 0.33f)
                        .height(140.dp)
                        .background(color = Color(0xFFBFBFBF)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "2",
                        style = TextStyle(
                            fontSize = 80.sp,
                            fontFamily = ThemeFonts.Gilroy,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (players.isNotEmpty()) {
                    Box(contentAlignment = Alignment.TopCenter) {
                        PlayerAvatar(
                            player = players[0],
                            modifier = Modifier.padding(bottom = 6.dp)
                        )

                        Text(
                            text = "👑",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.offset(y = (-16).dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(boxWithConstraintsScope.maxWidth * 0.34f)
                        .height(180.dp)
                        .background(color = Color(0xFFDCA622)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "1",
                        style = TextStyle(
                            fontSize = 90.sp,
                            fontFamily = ThemeFonts.Gilroy,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (players.size > 2) {
                    PlayerAvatar(
                        player = players[2],
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(boxWithConstraintsScope.maxWidth * 0.33f)
                        .height(100.dp)
                        .background(color = Color(0xFF8C5926)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "3",
                        style = TextStyle(
                            fontSize = 70.sp,
                            fontFamily = ThemeFonts.Gilroy,
                            fontWeight = FontWeight.Bold,
                        ),
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}
