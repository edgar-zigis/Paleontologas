package com.zigis.paleontologas.core.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.R
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme

@Composable
fun StaticScaffold(
    title: String,
    @DrawableRes
    iconResId: Int = R.drawable.ic_ammonite,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold {
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
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .systemBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(74.dp)
                ) {
                    Icon(
                        painter = painterResource(id = iconResId),
                        contentDescription = "back",
                        tint = ApplicationTheme.colors.tintColor,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 16.dp, top = 6.dp)
                            .size(32.dp)
                    )

                    Text(
                        text = title,
                        color = ApplicationTheme.colors.tintColor,
                        style = ApplicationTheme.typography.headline2,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 6.dp, end = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.bg_top_shadow),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    content()
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun StaticScaffoldPreview() {
    StaticScaffold(title = "Title") {
        //  here content is being rendered
    }
}