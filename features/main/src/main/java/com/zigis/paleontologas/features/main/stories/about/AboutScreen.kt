package com.zigis.paleontologas.features.main.stories.about

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.NavigableScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.main.R
import com.zigis.paleontologas.features.main.stories.about.list.AboutListItem
import com.zigis.paleontologas.features.main.stories.about.list.AboutListItemView
import org.koin.androidx.compose.koinViewModel

@Composable
fun AboutScreen(
    viewModel: AboutViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    AboutScreenUiImplementation(
        viewState = state
    ) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(
                AboutIntent.Initialize
            )
        }
    }
}

@Composable
private fun AboutScreenUiImplementation(
    viewState: AboutViewState,
    sendIntent: (AboutIntent) -> Unit?
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    NavigableScaffold(
        title = stringResource(id = R.string.about_app),
        onBack = {
            sendIntent(AboutIntent.InvokeBack)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(id = R.string.about_app_text_1),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.content,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
                    .padding(top = 6.dp)
            )

            Text(
                text = stringResource(id = R.string.wall_of_fame),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.headline3,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
                    .padding(top = 6.dp)
            )

            val rowCount = viewState.contributorItems.count() / 2

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(166.dp * rowCount)
            ) {
                items(viewState.contributorItems) { item ->
                    AboutListItemView(item = item)
                }
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
            )

            Text(
                text = stringResource(id = R.string.wall_of_fame_description),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.content,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .background(ApplicationTheme.colors.headingText)
                    .padding(top = 6.dp)
            )

            Text(
                text = getAboutBottomText(context),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.content,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 8.dp, top = 14.dp, end = 8.dp, bottom = 14.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.logo_grey),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = context.getString(
                    R.string.version_placeholder,
                    viewState.applicationVersion
                ),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
private fun getAboutBottomText(context: Context): AnnotatedString {
    return buildAnnotatedString {
        val str = context.getString(R.string.about_app_text_2)
        append(str)

        listOf(
            context.getString(R.string.app_contributor_11)
        ).forEach {
            addStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold
                ),
                start = str.indexOf(it),
                end = str.indexOf(it) + it.length
            )
        }

        context.getString(R.string.contact_email).let {
            addStyle(
                style = SpanStyle(
                    fontStyle = FontStyle.Italic
                ),
                start = str.indexOf(it),
                end = str.indexOf(it) + it.length
            )
            addUrlAnnotation(
                UrlAnnotation("mailto:edgar.zigis@gmail.com"),
                start = str.indexOf(it),
                end = str.indexOf(it) + it.length
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun AboutScreenPreview() {
    AboutScreenUiImplementation(
        viewState = AboutViewState(
            contributorItems = listOf(
                AboutListItem(
                    photoResId = R.drawable.photo_edgar_zigis,
                    fullNameResId = R.string.app_contributor_1,
                    contributionResId = R.string.app_contributor_description_1
                ),
                AboutListItem(
                    photoResId = R.drawable.photo_andrej_spiridonov,
                    fullNameResId = R.string.app_contributor_2,
                    contributionResId = R.string.app_contributor_description_2
                )
            ),
            applicationVersion = "2.5.2"
        )
    ) {
        //  here intents are being sent
    }
}