package com.zigis.paleontologas.features.main.stories.about.list

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.main.R

@Composable
fun AboutListItemView(
    item: AboutListItem
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = ApplicationTheme.colors.contentBackground)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = item.photoResId),
            contentDescription = null,
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 12.dp, top = 8.dp, end = 12.dp)
        ) {
            Text(
                text = stringResource(id = item.fullNameResId),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.title2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Text(
                text = stringResource(id = item.contributionResId),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.subtitle2,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LanguageListItemPreview() {
    AboutListItemView(
        item = AboutListItem(
            photoResId = R.drawable.photo_edgar_zigis,
            fullNameResId = R.string.app_contributor_1,
            contributionResId = R.string.app_contributor_description_1
        )
    )
}