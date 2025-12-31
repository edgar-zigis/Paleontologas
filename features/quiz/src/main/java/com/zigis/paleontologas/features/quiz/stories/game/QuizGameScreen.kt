package com.zigis.paleontologas.features.quiz.stories.game

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.rememberDebouncedClick
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.data.Question
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizGameScreen(
    viewModel: QuizGameViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizGameScreenUiImplementation(
        viewState = state
    ) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(QuizGameIntent.Initialize)
        }
    }
}

@Composable
private fun QuizGameScreenUiImplementation(
    viewState: QuizGameViewState,
    sendIntent: (QuizGameIntent) -> Unit?
) {
    val context = LocalContext.current
    var showBackConfirmationDialog by remember { mutableStateOf(false) }

    BackHandler {
        showBackConfirmationDialog = true
    }

    if (showBackConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showBackConfirmationDialog = false },
            title = { Text(stringResource(R.string.progress_wont_be_counted)) },
            text = { Text(stringResource(R.string.do_you_really_want_to_exit)) },
            confirmButton = {
                Button(
                    onClick = {
                        showBackConfirmationDialog = false
                        sendIntent(QuizGameIntent.InvokeBack)
                    }
                ) {
                    Text(stringResource(R.string.exit_confirmation))
                }
            },
            dismissButton = {
                Button(onClick = { showBackConfirmationDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    NavigableScaffold(
        title = stringResource(id = R.string.quiz),
        onBack = {
            showBackConfirmationDialog = true
        }
    ) {
        if (viewState.question == null) {
            return@NavigableScaffold
        }

        val alpha: Float by animateFloatAsState(if (viewState.isInTransition) 0f else 1f, label = "alpha")

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
        ) {
            val (artwork, question, answerOptions) = createRefs()
            val artworkResource = painterResource(id = context.getDrawableId(viewState.question.artwork))

            Image(
                artworkResource,
                contentDescription = "Quiz image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(
                        artworkResource.intrinsicSize.width / artworkResource.intrinsicSize.height
                    )
                    .graphicsLayer(alpha = alpha)
                    .constrainAs(artwork) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = context.getString(
                    "${viewState.question.periodName}_question_${viewState.question.questionIndex}"
                ),
                color = ApplicationTheme.colors.headingText,
                style = TextStyle(
                    fontSize = 19.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Normal
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .graphicsLayer(alpha = alpha)
                    .constrainAs(question) {
                        top.linkTo(artwork.bottom)
                        bottom.linkTo(answerOptions.top)
                    }
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.42f)
                    .graphicsLayer(alpha = alpha)
                    .constrainAs(answerOptions) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                QuizButton(index = 0, viewState) {
                    sendIntent(it)
                }

                QuizButton(index = 1, viewState) {
                    sendIntent(it)
                }

                QuizButton(index = 2, viewState) {
                    sendIntent(it)
                }

                QuizButton(index = 3, viewState) {
                    sendIntent(it)
                }
            }
        }
    }
}

@Composable
private fun ColumnScope.QuizButton(
    index: Int,
    viewState: QuizGameViewState,
    sendIntent: (QuizGameIntent) -> Unit?
) {
    val context = LocalContext.current

    val backgroundColor = when {
        viewState.chosenOption == index -> {
            if (viewState.chosenOption == viewState.correctOption) {
                ThemeColors.Success
            } else ThemeColors.Failure
        }
        viewState.correctOption == index -> ThemeColors.Success
        else -> ApplicationTheme.colors.backgroundPrimary
    }

    val debouncedClick = rememberDebouncedClick()

    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(bottom = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = ApplicationTheme.colors.contentBackground
        ),
        shape = RoundedCornerShape(4.dp),
        onClick = {
            debouncedClick {
                if (viewState.chosenOption != null) return@debouncedClick
                sendIntent(
                    QuizGameIntent.AnswerQuestion(
                        question = viewState.question!!,
                        option = index
                    )
                )
            }
        }
    ) {
        Text(
            text = context.getString(viewState.question!!.variantList[index]),
            style = ApplicationTheme.typography.title1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuizGameScreenPreview() {
    val mockedQuestion = Question(
        id = 1,
        periodId = 1,
        periodName = "hadean",
        questionIndex = 3,
        artwork = "item_hadean",
        category = "fossil",
        isAnswered = false
    ).also {
        it.variantList.addAll(
            listOf(
                it.periodName + "_variant_" + it.questionIndex + "_1",
                it.periodName + "_variant_" + it.questionIndex + "_2",
                it.periodName + "_variant_" + it.questionIndex + "_3",
                it.periodName + "_variant_" + it.questionIndex + "_4"
            )
        )
        it.variantList.shuffle()
    }
    QuizGameScreenUiImplementation(
        viewState = QuizGameViewState(
            question = mockedQuestion,
            chosenOption = 2,
            correctOption = mockedQuestion.getCorrectVariantIndex()
        )
    ) {
        //  here intents are being sent
    }
}