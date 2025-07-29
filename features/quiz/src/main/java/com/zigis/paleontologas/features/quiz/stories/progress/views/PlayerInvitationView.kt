package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.extensions.rememberDebouncedClick
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.quiz.R

@Composable
fun PlayerInvitationView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        ThemeColors.Success,
                        ThemeColors.Success.copy(alpha = 0.8f)
                    )
                ),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.leaderboard_invitation_title),
            color = Color.White,
            style = ApplicationTheme.typography.title2
        )

        Text(
            text = stringResource(id = R.string.leaderboard_invitation_description),
            color = Color.White.copy(alpha = 0.7f),
            style = ApplicationTheme.typography.content,
            textAlign = TextAlign.Center
        )

        val debouncedClick = rememberDebouncedClick()

        ElevatedButton(
            modifier = Modifier
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ApplicationTheme.colors.backgroundPrimary,
                contentColor = ApplicationTheme.colors.contentBackground
            ),
            onClick = {
                debouncedClick {
                    onClick.invoke()
                }
            }
        ) {
            Text(stringResource(id = R.string.leaderboard_invitation_button_label))
        }
    }
}
