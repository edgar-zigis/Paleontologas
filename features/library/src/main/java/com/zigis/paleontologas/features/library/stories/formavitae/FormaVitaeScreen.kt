package com.zigis.paleontologas.features.library.stories.formavitae

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.library.R
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun FormaVitaeScreen(
    viewModel: FormaVitaeViewModel = koinViewModel(),
    configuration: FormaVitaeConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FormaVitaeScreenUiImplementation(viewState = state) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(FormaVitaeIntent.Initialize(
                lifeFormId = configuration.lifeFormId
            ))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormaVitaeScreenUiImplementation(
    viewState: FormaVitaeViewState,
    sendIntent: (FormaVitaeIntent) -> Unit?
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .background(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            ApplicationTheme.colors.backgroundSecondary,
                            ApplicationTheme.colors.backgroundPrimary
                        )
                    )
                )
                .navigationBarsPadding()
        ) {
            Column(modifier = Modifier.padding(it)) {
                TopAppBar(
                    title = {
                        Text(
                            text = context.getString(viewState.title),
                            color = ThemeColors.LightThemeColors.titleText,
                            style = ApplicationTheme.typography.headline2,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            sendIntent(FormaVitaeIntent.InvokeBack)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_back_button),
                                contentDescription = "back",
                                tint = ThemeColors.LightThemeColors.titleText
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    ),
                    scrollBehavior = scrollBehavior
                )

                Image(
                    painter = painterResource(id = R.drawable.bg_top_shadow),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )

                if (viewState.artwork.isBlank()) {
                    return@Column
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                        .background(ThemeColors.LightThemeColors.contentBackground)
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
                            color = ThemeColors.LightThemeColors.headingTextSecondary,
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
                            .background(ThemeColors.LightThemeColors.contentBackground)
                    ) {
                        Text(
                            text = context.getString(viewState.title),
                            color = ThemeColors.LightThemeColors.contentText,
                            style = ApplicationTheme.typography.headline1,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 6.dp)
                        )

                        Text(
                            text = context.getString(R.string.mya, viewState.timeScale),
                            color = ThemeColors.LightThemeColors.contentText,
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
                                .background(ThemeColors.LightThemeColors.headingText)
                                .padding(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_description),
                                contentDescription = null
                            )
                            Text(
                                text = stringResource(id = R.string.general_information),
                                color = ThemeColors.LightThemeColors.headingTextSecondary,
                                style = ApplicationTheme.typography.title1,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            )
                        }

                        Text(
                            text = context.getString(viewState.description),
                            color = ThemeColors.LightThemeColors.contentText,
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
                                    color = ThemeColors.LightThemeColors.headingTextSecondary,
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
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun FormaVitaeScreenPreview() {
    FormaVitaeScreenUiImplementation(
        viewState = FormaVitaeViewState(
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