package com.zigis.paleontologas.features.library.stories.formavitae

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
                        .fillMaxWidth()
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
                }
            }
        }
    }
}