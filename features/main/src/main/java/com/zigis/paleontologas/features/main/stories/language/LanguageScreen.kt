package com.zigis.paleontologas.features.main.stories.language

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.PaleoScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.stories.language.list.LanguageListItem
import com.zigis.paleontologas.features.main.stories.language.list.LanguageListItemView
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

@Composable
fun LanguageScreen(
    viewModel: LanguageViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LanguageScreenUiImplementation(
        items = state.localeItems,
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

@Composable
private fun LanguageScreenUiImplementation(
    items: List<LanguageListItem>,
    currentLocale: Locale?,
    sendIntent: (LanguageIntent) -> Unit?
) {
    val context = LocalContext.current

    PaleoScaffold(
        title = context.getString(R.string.language),
        onBack = {
            sendIntent(LanguageIntent.InvokeBack)
        }
    ) {
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
                )
                .background(ApplicationTheme.colors.contentBackground)
        ) {
            items(items) { item ->
                LanguageListItemView(
                    item = item,
                    isSelected = item.locale == currentLocale,
                    onClick = {
                        sendIntent(LanguageIntent.ChangeLocale(locale = item.locale))
                    }
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LanguageScreenPreview() {
    LanguageScreenUiImplementation(
        items = listOf(
            LanguageListItem(Locale.GERMAN),
            LanguageListItem(Locale.ENGLISH),
            LanguageListItem(Locale.FRENCH),
            LanguageListItem(Locale.ITALIAN)
        ),
        currentLocale = Locale.ENGLISH
    ) {
        //  here intents are being sent
    }
}