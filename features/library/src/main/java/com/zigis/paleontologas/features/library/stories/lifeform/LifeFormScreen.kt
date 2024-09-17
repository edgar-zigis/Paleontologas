package com.zigis.paleontologas.features.library.stories.lifeform

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.parallaxLayoutModifier
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.library.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun LifeFormScreen(
    viewModel: LifeFormViewModel = koinViewModel(),
    configuration: LifeFormConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifeFormScreenUiImplementation(viewState = state) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(
                LifeFormScreenIntent.Initialize(
                    lifeFormId = configuration.lifeFormId
                )
            )
        }
    }
}

@Composable
private fun LifeFormScreenUiImplementation(
    viewState: LifeFormScreenState,
    sendIntent: (LifeFormScreenIntent) -> Unit?
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    NavigableScaffold(
        title = context.getString(viewState.title),
        onBack = {
            sendIntent(LifeFormScreenIntent.InvokeBack)
        }
    ) {
        if (viewState.artwork.isBlank()) {
            return@NavigableScaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
                .verticalScroll(scrollState),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .parallaxLayoutModifier(scrollState, 2)
            ) {
                Image(
                    painterResource(id = context.getDrawableId(viewState.artwork)),
                    contentDescription = "LifeForm image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = context.getString(viewState.artworkAuthor),
                    color = ApplicationTheme.colors.headingTextSecondary,
                    style = ApplicationTheme.typography.caption1,
                    maxLines = 1,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 4.dp, end = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ApplicationTheme.colors.contentBackground)
            ) {
                Text(
                    text = context.getString(viewState.title),
                    color = ApplicationTheme.colors.contentText,
                    style = ApplicationTheme.typography.headline1,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 6.dp)
                )

                Text(
                    text = context.getString(R.string.mya, viewState.timeScale),
                    color = ApplicationTheme.colors.contentText,
                    style = ApplicationTheme.typography.subtitle1,
                    fontStyle = FontStyle.Italic,
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .background(ApplicationTheme.colors.headingText)
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_description),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.general_information),
                        color = ApplicationTheme.colors.headingTextSecondary,
                        style = ApplicationTheme.typography.title1,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }

                Text(
                    text = context.getString(viewState.description),
                    color = ApplicationTheme.colors.contentText,
                    style = ApplicationTheme.typography.content,
                    modifier = Modifier
                        .padding(8.dp)
                )

                if (viewState.additionalArtwork.isNotBlank()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painterResource(id = context.getDrawableId(viewState.additionalArtwork)),
                            contentDescription = "LifeForm image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Text(
                            text = context.getString(viewState.additionalArtworkAuthor),
                            color = ApplicationTheme.colors.headingTextSecondary,
                            style = ApplicationTheme.typography.caption2,
                            maxLines = 1,
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(bottom = 4.dp, end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LifeFormScreenPreview() {
    LifeFormScreenUiImplementation(
        viewState = LifeFormScreenState(
            title = "quaternary_mammutus",
            artwork = "item_quaternary_mammuthus",
            artworkAuthor = "",
            timeScale = "5.332-0.0037",
            description = "quaternary_mammutus_description",
            additionalArtworkAuthor = "",
            additionalArtwork = "item_quaternary_mammuthus_info"
        )
    ) {
        //  here intents are being sent
    }
}