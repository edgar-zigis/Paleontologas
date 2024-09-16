package com.zigis.paleontologas.features.main.stories.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.StaticScaffold
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.stories.home.list.HomeListItem
import com.zigis.paleontologas.features.main.stories.home.list.HomeListItemView
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
    val context = LocalContext.current

    StaticScaffold(
        title = context.getString(R.string.geological_time_scale),
        iconResId = R.drawable.ic_ammonite
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mammoth),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .alpha(0.4f)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues = PaddingValues(
                        start = 8.dp,
                        top = 0.dp,
                        end = 8.dp,
                        bottom = 8.dp
                    )
                ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(viewState.periodItems) { item ->
                HomeListItemView(
                    item = item,
                    modifier = Modifier.animateItem(),
                    onClick = {
                        sendIntent(HomeIntent.OpenPeriod(periodId = it))
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.size(32.dp))
            }
        }
    }
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