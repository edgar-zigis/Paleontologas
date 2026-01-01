package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R

@Composable
fun PlayerRankingView(
    modifier: Modifier = Modifier,
    ranking: Int
) {
    val targetWidth = min(
        (LocalWindowInfo.current.containerSize.width.dp - 48.dp),
        420.dp
    )

    Box(
        modifier = modifier
            .padding(16.dp)
            .width(targetWidth)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ranking),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(
                    start = 50.dp,
                    end = 60.dp,
                    top = 24.dp,
                    bottom = 24.dp
                )
        ) {
            Text(
                text = stringResource(R.string.leaderboard_global_ranking_title, ranking),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = stringResource(R.string.leaderboard_global_ranking_description),
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}