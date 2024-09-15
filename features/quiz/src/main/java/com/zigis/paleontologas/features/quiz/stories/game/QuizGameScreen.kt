package com.zigis.paleontologas.features.quiz.stories.game

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.PaleoScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
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

    PaleoScaffold(
        title = stringResource(id = R.string.quiz),
        onBack = {
            sendIntent(QuizGameIntent.InvokeBack)
        }
    ) {
        if (viewState.question == null) {
            return@PaleoScaffold
        }

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
        ) {
            val (artwork, question, answerOptions) = createRefs()

            Image(
                painterResource(id = context.getDrawableId(viewState.question.artwork)),
                contentDescription = "LifeForm image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(artwork) {
                        top.linkTo(parent.top)
                    }
            )

            Text(
                text = context.getString(
                    "${viewState.question.periodName}_question_${viewState.question.questionIndex}"
                ),
                color = ApplicationTheme.colors.headingText,
                style = ApplicationTheme.typography.title1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(question) {
                        top.linkTo(artwork.bottom)
                        bottom.linkTo(answerOptions.top)
                    }
            )

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.4f)
                    .constrainAs(answerOptions) {
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ApplicationTheme.colors.backgroundPrimary,
                        contentColor = ApplicationTheme.colors.contentBackground
                    ),
                    onClick = {
                        sendIntent(
                            QuizGameIntent.AnswerQuestion(
                                question = viewState.question,
                                option = 0
                            )
                        )
                    }
                ) {
                    Text(text = context.getString(viewState.question.variantList[0]))
                }

                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ApplicationTheme.colors.backgroundPrimary,
                        contentColor = ApplicationTheme.colors.contentBackground
                    ),
                    onClick = {
                        sendIntent(
                            QuizGameIntent.AnswerQuestion(
                                question = viewState.question,
                                option = 1
                            )
                        )
                    }
                ) {
                    Text(text = context.getString(viewState.question.variantList[1]))
                }

                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ApplicationTheme.colors.backgroundPrimary,
                        contentColor = ApplicationTheme.colors.contentBackground
                    ),
                    onClick = {
                        sendIntent(
                            QuizGameIntent.AnswerQuestion(
                                question = viewState.question,
                                option = 2
                            )
                        )
                    }
                ) {
                    Text(text = context.getString(viewState.question.variantList[2]))
                }

                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(bottom = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ApplicationTheme.colors.backgroundPrimary,
                        contentColor = ApplicationTheme.colors.contentBackground
                    ),
                    onClick = {
                        sendIntent(
                            QuizGameIntent.AnswerQuestion(
                                question = viewState.question,
                                option = 3
                            )
                        )
                    }
                ) {
                    Text(text = context.getString(viewState.question.variantList[3]))
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuizGameScreenPreview() {
    QuizGameScreenUiImplementation(
        viewState = QuizGameViewState(
            question = Question(
                id = 1,
                periodId = 1,
                periodName = "hadean",
                questionIndex = 3,
                artwork = "item_hadean",
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
        )
    ) {
        //  here intents are being sent
    }
}