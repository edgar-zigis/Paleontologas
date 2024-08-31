package com.zigis.paleontologas.features.library.stories.geologicalperiod.list

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getStringId
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

@Composable
fun GeologicalPeriodListItemView(
    item: GeologicalPeriodListItem,
    onClick: (Int) -> Unit
) {
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(144.dp)
        .background(color = ApplicationTheme.colors.contentBackground)
        .clickable(enabled = true) {
            onClick.invoke(item.id)
        }
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = context.getDrawableId(item.thumbnail)),
            contentDescription = null,
            modifier = Modifier
                .size(82.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 4.dp, end = 4.dp)
        ) {
            Text(
                text = stringResource(context.getStringId(item.title)),
                color = ApplicationTheme.colors.contentText,
                style = ApplicationTheme.typography.title3,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LanguageListItemPreview() {
    GeologicalPeriodListItemView(item = GeologicalPeriodListItem(
        id = 0,
        title = "quaternary_mammutus",
        thumbnail = "item_quaternary_mammuthus_thumb"
    )) {
        //  ignore
    }
}