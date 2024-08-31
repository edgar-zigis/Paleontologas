package com.zigis.paleontologas.features.library.stories.geologicalperiod

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.zigis.paleontologas.features.library.stories.geologicalperiod.list.GeologicalPeriodListItemView
import org.koin.androidx.compose.koinViewModel

@Composable
fun GeologicalPeriodScreen(
    viewModel: GeologicalPeriodViewModel = koinViewModel(),
    configuration: GeologicalPeriodConfiguration
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    GeologicalPeriodScreenUiImplementation(viewState = state) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(
                GeologicalPeriodIntent.Initialize(
                    periodId = configuration.periodId
            ))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GeologicalPeriodScreenUiImplementation(
    viewState: GeologicalPeriodViewState,
    sendIntent: (GeologicalPeriodIntent) -> Unit?
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
                            sendIntent(GeologicalPeriodIntent.InvokeBack)
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
                                painter = painterResource(id = R.drawable.ic_physics),
                                contentDescription = null
                            )
                            Text(
                                text = stringResource(id = R.string.environment),
                                color = ThemeColors.LightThemeColors.headingTextSecondary,
                                style = ApplicationTheme.typography.title1,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            )
                        }

                        Text(
                            text = context.getString(viewState.environmentDescription),
                            color = ThemeColors.LightThemeColors.contentText,
                            style = ApplicationTheme.typography.content,
                            modifier = Modifier
                                .padding(8.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
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

                        if (viewState.map.isNotBlank()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            ) {
                                Image(
                                    painterResource(id = context.getDrawableId(viewState.map)),
                                    contentDescription = "LifeForm image",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )

                                Text(
                                    text = when (viewState.periodId) {
                                        3 -> context.getString(R.string.map_author_1)
                                        else -> context.getString(R.string.map_author_2)
                                    },
                                    color = ThemeColors.LightThemeColors.headingTextSecondary,
                                    style = ApplicationTheme.typography.caption2,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .align(Alignment.BottomEnd)
                                        .padding(bottom = 4.dp, end = 8.dp)
                                )
                            }
                        }

                        if (context.getString(viewState.additionalTitle).isNotBlank()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(ThemeColors.LightThemeColors.headingText)
                                    .padding(4.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_additional),
                                    contentDescription = null
                                )
                                Text(
                                    text = context.getString(viewState.additionalTitle),
                                    color = ThemeColors.LightThemeColors.headingTextSecondary,
                                    style = ApplicationTheme.typography.title1,
                                    maxLines = 1,
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                )
                            }

                            Text(
                                text = context.getString(viewState.additionalDescription),
                                color = ThemeColors.LightThemeColors.contentText,
                                style = ApplicationTheme.typography.content,
                                modifier = Modifier
                                    .padding(8.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(ThemeColors.LightThemeColors.headingText)
                                .padding(4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_life),
                                contentDescription = null
                            )
                            Text(
                                text = stringResource(id = R.string.life_forms),
                                color = ThemeColors.LightThemeColors.headingTextSecondary,
                                style = ApplicationTheme.typography.title1,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 4.dp)
                            )
                        }

                        Text(
                            text = context.getString(viewState.lifeFormDescription),
                            color = ThemeColors.LightThemeColors.contentText,
                            style = ApplicationTheme.typography.content,
                            modifier = Modifier
                                .padding(8.dp)
                        )

                        if (viewState.lifeFormItems.isNotEmpty()) {
                            val rowCount = viewState.lifeFormItems.count() / 2

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .background(ThemeColors.LightThemeColors.headingText)
                            )

                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                userScrollEnabled = false,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(144.dp * rowCount + 6.dp * (rowCount - 1))
                                    .background(ThemeColors.LightThemeColors.headingText)
                            ) {
                                items(viewState.lifeFormItems) { item ->
                                    GeologicalPeriodListItemView(item = item) {
                                        sendIntent(GeologicalPeriodIntent.OpenLifeForm(it))
                                    }
                                }
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
private fun GeologicalPeriodScreenPreview() {
    GeologicalPeriodScreenUiImplementation(
        viewState = GeologicalPeriodViewState(
            periodId = 0,
            title = "quaternary",
            artwork = "item_quaternary",
            artworkAuthor = "© Masato Hattori",
            timeScale = "2.58–0",
            environmentDescription = "quaternary_physics",
            description = "quaternary_description",
            map = "item_quaternary_map_1",
            additionalTitle = "quaternary_additional_title",
            additionalDescription = "quaternary_additional",
            lifeFormDescription = "quaternary_life_forms"
        )
    ) {
        //  here intents are being sent
    }
}