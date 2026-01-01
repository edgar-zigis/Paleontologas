package com.zigis.paleontologas.features.quiz.stories.categoryselection

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.data.Question
import com.zigis.paleontologas.features.quiz.entities.QuizCategory
import com.zigis.paleontologas.features.quiz.stories.categoryselection.views.QuizCategoryCardView
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressIntent
import com.zigis.paleontologas.features.quiz.stories.progress.views.PaywallSheet
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizCategoryScreen(
    viewModel: QuizCategoryViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    QuizCategoryScreenUiImplementation(
        viewState = state,
        sendIntent = { viewModel.intents.sendSafely(it) }
    )

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(QuizCategoryIntent.Initialize)
        }
    }
}

@Composable
private fun QuizCategoryScreenUiImplementation(
    viewState: QuizCategoryViewState,
    sendIntent: (QuizCategoryIntent) -> Unit?
) {
    val scrollState = rememberScrollState()

    NavigableScaffold(
        title = stringResource(id = R.string.choose_category),
        onBack = { sendIntent(QuizCategoryIntent.InvokeBack) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            viewState.generalQuizCategory?.let { generalCategory ->
                QuizCategoryCardView(
                    category = generalCategory,
                    onTap = {
                        sendIntent(QuizCategoryIntent.ChooseCategory(category = null))
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)  // Swift: .padding(.top, 16)
                        .padding(horizontal = 8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.tailored_quiz).uppercase(),
                    color = ApplicationTheme.colors.backgroundPrimary,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ThemeFonts.Gilroy,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            viewState.tailoredQuizCategories.forEach { item ->
                QuizCategoryCardView(
                    category = item,
                    onTap = {
                        sendIntent(QuizCategoryIntent.ChooseCategory(category = item.category))
                    },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (viewState.displayPaywallSheet) {
            PaywallSheet(onDismiss = {
                sendIntent(QuizCategoryIntent.DismissPaywall(attemptPurchase = false))
            }) {
                sendIntent(QuizCategoryIntent.DismissPaywall(attemptPurchase = true))
            }
        }
    }
}

@Preview(uiMode = android.content.res.Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun QuizCategoryScreenPreview() {
    QuizCategoryScreenUiImplementation(
        viewState = QuizCategoryViewState(
            generalQuizCategory = QuizCategory(
                id = 0,
                title = R.string.general_category,
                category = null,
                answeredCount = 3,
                totalCount = 10
            ),
            tailoredQuizCategories = listOf(
                QuizCategory(1, R.string.fossil_category, Question.Category.FOSSIL, 2, 5),
                QuizCategory(2, R.string.mesozoic_category, Question.Category.MESOZOIC, 1, 8)
            )
        ),
        sendIntent = {  }
    )
}
