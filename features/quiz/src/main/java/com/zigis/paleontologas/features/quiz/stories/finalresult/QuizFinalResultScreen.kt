package com.zigis.paleontologas.features.quiz.stories.finalresult

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zigis.paleontologas.core.extensions.rememberDebouncedClick
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.utilities.QuizMarkUtility
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizFinalResultScreen(
    viewModel: QuizFinalResultViewModel = koinViewModel(),
    configuration: QuizFinalResultConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizFinalResultScreenUiImplementation(
        viewState = state
    ) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(QuizFinalResultIntent.Initialize(
                mark = configuration.mark
            ))
        }
    }
}

@Composable
private fun QuizFinalResultScreenUiImplementation(
    viewState: QuizFinalResultViewState,
    sendIntent: (QuizFinalResultIntent) -> Unit?
) {
    val context = LocalContext.current

    NavigableScaffold(
        title = stringResource(id = R.string.finito),
        onBack = {
            sendIntent(QuizFinalResultIntent.InvokeBack)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
                QuizMarkUtility.getAnimationResource(viewState.mark)
            ))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.height(240.dp)
            )

            Text(
                text = stringResource(id = R.string.correct_answered),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.title1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            Text(
                text = context.getString(
                    R.string.quiz_mark_placeholder,
                    viewState.mark,
                    viewState.totalQuestions
                ),
                color = ApplicationTheme.colors.contentText,
                style = TextStyle(
                    fontSize = 36.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            if (viewState.isParticipatingInLeaderboard) {
                Text(
                    text = context.getString(
                        R.string.leaderboard_xp_earned_label,
                        if (viewState.mark == 10) 20 else viewState.mark
                    ),
                    color = ThemeColors.Success,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = ThemeFonts.Gilroy,
                        fontWeight = FontWeight.Medium
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                )
            }

            Text(
                text = context.getString(
                    QuizMarkUtility.getMarkDescription(viewState.mark)
                ),
                color = ApplicationTheme.colors.contentText,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 16.dp, end = 8.dp)
            )

            val debouncedClick = rememberDebouncedClick()

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 32.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ApplicationTheme.colors.backgroundPrimary,
                    contentColor = ApplicationTheme.colors.contentBackground
                ),
                onClick = {
                    debouncedClick {
                        sendIntent(QuizFinalResultIntent.InvokeBack)
                    }
                }
            ) {
                Text(stringResource(id = R.string.continue_further))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuizFinalResultScreenPreview() {
    QuizFinalResultScreenUiImplementation(
        viewState = QuizFinalResultViewState(
            mark = 8,
            totalQuestions = 10,
            isParticipatingInLeaderboard = true
        )
    ) {
        //  here intents are being sent
    }
}