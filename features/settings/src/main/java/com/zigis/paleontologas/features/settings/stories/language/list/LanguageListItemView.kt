package com.zigis.paleontologas.features.settings.stories.language.list

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getStringId
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import java.util.Locale

@Composable
fun LanguageListItemView(
    item: LanguageListItem,
    isSelected: Boolean,
    onClick: (Locale) -> Unit
) {
    val context = LocalContext.current

    val identifier = if (!item.locale.country.isNullOrEmpty()) {
        item.locale.country.lowercase()
    } else item.locale.language

    Row(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .background(
            color = ApplicationTheme.colors.backgroundSecondary.copy(
                alpha = if (isSelected) 0.08f else 0f
            )
        )
        .clickable(enabled = true) {
            onClick.invoke(item.locale)
        }
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = context.getDrawableId("ic_flag_$identifier")),
            contentDescription = null,
            modifier = Modifier
                .size(34.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = stringResource(context.getStringId("language_$identifier")),
            color = ApplicationTheme.colors.contentText,
            style = ApplicationTheme.typography.title1,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun LanguageListItemPreview() {
    LanguageListItemView(
        item = LanguageListItem(locale = Locale("lt")),
        isSelected = false
    ) {
        //  ignore
    }
}