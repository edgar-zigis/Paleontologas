package com.zigis.paleontologas.features.main.stories.language

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.stories.language.list.LanguageListItem
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun LanguageScreen(
    viewModel: LanguageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LanguageScreenUiImplementation(
        localeList = state.localeList,
        currentLocale = state.currentLocale
    ) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(LanguageIntent.Initialize)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageScreenUiImplementation(
    localeList: List<Locale>,
    currentLocale: Locale?,
    sendIntent: (LanguageIntent) -> Unit?
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )

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
                            text = stringResource(id = R.string.language),
                            color = ThemeColors.LightThemeColors.titleText,
                            style = ApplicationTheme.typography.headline2,
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            sendIntent(LanguageIntent.InvokeBack)
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

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(
                            paddingValues = PaddingValues(
                                start = 8.dp,
                                top = 0.dp,
                                end = 8.dp,
                                bottom = 8.dp
                            )
                        )
                        .background(Color.White)
                ) {
                    items(localeList) { locale ->
                        LanguageListItem(
                            item = locale,
                            isSelected = locale == currentLocale,
                            onClick = {
                                sendIntent(LanguageIntent.ChangeLocale(locale = locale))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LanguageScreenPreview() {
    LanguageScreenUiImplementation(
        localeList = listOf(Locale.GERMAN, Locale.ENGLISH, Locale.FRENCH, Locale.ITALIAN),
        currentLocale = Locale.ENGLISH
    ) {
        //  here intents are being sent
    }
}