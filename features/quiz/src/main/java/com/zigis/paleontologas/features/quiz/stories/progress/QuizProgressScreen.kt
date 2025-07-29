package com.zigis.paleontologas.features.quiz.stories.progress

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.ArcProgressbar
import com.zigis.paleontologas.core.ui.StaticScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.stories.progress.views.PlayerInvitationView
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizProgressScreen(
    viewModel: QuizProgressViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizProgressScreenUiImplementation(
        viewState = state
    ) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(QuizProgressIntent.Initialize)
        }
    }
}

@Composable
private fun QuizProgressScreenUiImplementation(
    viewState: QuizProgressViewState,
    sendIntent: (QuizProgressIntent) -> Unit?
) {
    val scrollState = rememberScrollState()

    StaticScaffold(
        title = stringResource(id = R.string.quiz),
        iconResId = R.drawable.ic_quiz
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(210.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                ArcProgressbar(
                    currentValue = viewState.progress,
                    label = stringResource(id = R.string.answered),
                    unit = "%",
                    thickness = 10.dp,
                    foregroundArcColor = Color.LightGray,
                    backgroundArcColor = ThemeColors.Success,
                    labelTextStyle = TextStyle(
                        fontSize = 15.sp,
                        fontFamily = ThemeFonts.Gentona,
                        color = ApplicationTheme.colors.contentText
                    ),
                    valueTextStyle = TextStyle(
                        fontSize = 60.sp,
                        fontFamily = ThemeFonts.Gentona,
                        color = ApplicationTheme.colors.contentText
                    ),
                    unitTextStyle = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = ThemeFonts.Gentona,
                        color = ApplicationTheme.colors.contentText
                    )
                )
            }

            Text(
                text = stringResource(id = R.string.quiz_welcome_text),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.content,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 24.dp, end = 8.dp, bottom = 32.dp)
            )

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 14.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ApplicationTheme.colors.backgroundPrimary,
                    contentColor = ApplicationTheme.colors.contentBackground
                ),
                onClick = { sendIntent(QuizProgressIntent.StartQuiz) }
            ) {
                Text(stringResource(id = R.string.start))
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
                    .padding(top = 6.dp)
            )

            Text(
                text = stringResource(id = R.string.leaderboard),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.headline3,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
                    .padding(top = 6.dp)
            )

            if (viewState.activeUser == null) {
                PlayerInvitationView {
                    sendIntent(QuizProgressIntent.CreateAccount)
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuizProgressScreenPreview() {
    QuizProgressScreenUiImplementation(
        viewState = QuizProgressViewState(
            progress = 60f
        )
    ) {
        //  here intents are being sent
    }
}