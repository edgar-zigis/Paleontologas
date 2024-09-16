package com.zigis.paleontologas.features.main.stories.home

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.features.main.stories.home.list.HomeListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenUiImplementation(viewState = state) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(HomeIntent.Initialize)
        }
    }
}

@Composable
private fun HomeScreenUiImplementation(
    viewState: HomeViewState,
    sendIntent: (HomeIntent) -> Unit?
) {
    //  TODO: Implementation
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HomeScreenPreview() {
    HomeScreenUiImplementation(
        viewState = HomeViewState(
            periodItems = listOf(
                HomeListItem(
                    id = 1,
                    title = "devonian",
                    thumbnail = "period_devonian",
                    shortDescription = "devonian_short_description",
                    timeScale = "419.2–358.9",
                    quizProgress = 90
                ),
                HomeListItem(
                    id = 2,
                    title = "carboniferous",
                    thumbnail = "period_carboniferous",
                    shortDescription = "carboniferous_short_description",
                    timeScale = "358.9–298.9",
                    quizProgress = 25
                ),
                HomeListItem(
                    id = 3,
                    title = "permian",
                    thumbnail = "period_permian",
                    shortDescription = "permian_short_description",
                    timeScale = "298.9–252.17",
                    quizProgress = 60
                )
            )
        )
    ) {
        //  here intents are being sent
    }
}