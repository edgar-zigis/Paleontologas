package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.features.quiz.entities.QuizPlayer

@Composable
fun PodiumView(players: List<QuizPlayer>) {
    BoxWithConstraints {
        val boxWithConstraintsScope = this

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
                        .background(color = Color(0xFFBFBFBF))
                        .shadow(10.dp, spotColor = Color.Black.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "2",
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Bold,
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

                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Crown",
                            tint = Color.Yellow,
                            modifier = Modifier.offset(y = (-54).dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(boxWithConstraintsScope.maxWidth * 0.34f)
                        .height(180.dp)
                        .background(color = Color(0xFFDCA622))
                        .shadow(10.dp, spotColor = Color.Black.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "1",
                        fontSize = 90.sp,
                        fontWeight = FontWeight.Bold,
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
                        .background(color = Color(0xFF8C5926))
                        .shadow(10.dp, spotColor = Color.Black.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Text(
                        text = "3",
                        fontSize = 70.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}
