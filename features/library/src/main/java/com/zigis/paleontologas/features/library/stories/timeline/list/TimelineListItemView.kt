package com.zigis.paleontologas.features.library.stories.timeline.list

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.zigis.paleontologas.core.extensions.getDrawableId
import com.zigis.paleontologas.core.extensions.getStringId
import com.zigis.paleontologas.core.ui.ArcProgressbar
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.library.R

@Composable
fun HomeListItemView(
    item: TimelineListItem,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit?
) {
    val context = LocalContext.current

    ConstraintLayout(modifier = modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(color = ApplicationTheme.colors.contentBackground)
        .clickable(enabled = true) {
            onClick.invoke(item.id)
        }
    ) {
        Image(
            painter = painterResource(id = context.getDrawableId(item.thumbnail)),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        val (title, description, timeScale, progress) = createRefs()

        Text(
            text = stringResource(context.getStringId(item.title)),
            color = ApplicationTheme.colors.tintColor,
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = ApplicationTheme.colors.contentText,
                    offset = Offset(2f, 2f)
                )
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp)
                .constrainAs(title) {
                    top.linkTo(parent.top)
                }
        )

        Text(
            text = stringResource(context.getStringId(item.shortDescription)),
            color = ApplicationTheme.colors.tintColor,
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                shadow = Shadow(
                    color = ApplicationTheme.colors.contentText,
                    offset = Offset(1f, 1f)
                )
            ),
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 2.dp, end = 24.dp)
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    bottom.linkTo(timeScale.top)
                }
        )

        Text(
            text = context.getString(R.string.mya, item.timeScale),
            color = ApplicationTheme.colors.tintColor,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = ThemeFonts.Gilroy,
                fontWeight = FontWeight.Normal,
                shadow = Shadow(
                    color = ApplicationTheme.colors.contentText,
                    offset = Offset(1f, 1f)
                )
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 2.dp)
                .constrainAs(timeScale) {
                    top.linkTo(description.bottom)
                    bottom.linkTo(parent.bottom)
                }
        )

        createVerticalChain(title, description, timeScale, chainStyle = ChainStyle.Packed)

        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(30.dp)
                .constrainAs(progress) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        ) {
            ArcProgressbar(
                currentValue = item.quizProgress.toFloat(),
                startAngle = 270f,
                sweepAngle = 90f,
                unit = "%",
                thickness = 2.dp,
                foregroundArcColor = Color.DarkGray,
                backgroundArcColor = Color.LightGray,
                valueTextStyle = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = ThemeFonts.Gentona,
                    color = ApplicationTheme.colors.tintColor
                )
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun HomeListItemViewPreview() {
    HomeListItemView(
        item = TimelineListItem(
            id = 1,
            title = "permian",
            thumbnail = "period_permian",
            shortDescription = "permian_short_description",
            timeScale = "298.9–252.17",
            quizProgress = 60
        ),
        modifier = Modifier
    ) {

    }
}