package com.zigis.paleontologas.features.settings.stories.settings

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zigis.paleontologas.core.extensions.debouncedClickable
import com.zigis.paleontologas.core.extensions.sendSafely
import com.zigis.paleontologas.core.providers.LifecycleEventHandler
import com.zigis.paleontologas.core.ui.StaticScaffold
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeFonts
import com.zigis.paleontologas.features.settings.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SettingsScreenUiImplementation(viewState = state) {
        viewModel.intents.sendSafely(it)
    }

    LifecycleEventHandler {
        if (it == Lifecycle.Event.ON_START) {
            viewModel.intents.sendSafely(SettingsScreenIntent.Initialize)
        }
    }
}

@Composable
private fun SettingsScreenUiImplementation(
    viewState: SettingsScreenState,
    sendIntent: (SettingsScreenIntent) -> Unit?
) {
    StaticScaffold(
        title = stringResource(id = R.string.settings),
        iconResId = R.drawable.ic_settings
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                .background(ApplicationTheme.colors.contentBackground)
        ) {
            Text(
                text = stringResource(id = R.string.settings),
                color = ApplicationTheme.colors.backgroundPrimary,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            )

            ClickableItem(
                iconRes = R.drawable.ic_language,
                titleRes = R.string.language
            ) {
                sendIntent(SettingsScreenIntent.OpenLanguages)
            }

            ToggleableItem(
                iconRes = R.drawable.ic_vibrate,
                titleRes = R.string.vibration_enabled,
                isEnabled = viewState.isVibrationEnabled
            ) {
                sendIntent(SettingsScreenIntent.ToggleVibration(it))
            }

            Text(
                text = stringResource(id = R.string.about_app),
                color = ApplicationTheme.colors.backgroundPrimary,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = ThemeFonts.Gilroy,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
            )

            ClickableItem(
                iconRes = R.drawable.ic_info,
                titleRes = R.string.info
            ) {
                sendIntent(SettingsScreenIntent.OpenAbout)
            }
        }
    }
}

@Composable
private fun ToggleableItem(
    @DrawableRes
    iconRes: Int,
    @StringRes
    titleRes: Int,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit?
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .debouncedClickable {
            onToggle(!isEnabled)
        }
    ) {
        val (icon, title, switch, separator) = createRefs()

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(20.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            colorFilter = ColorFilter.tint(
                color = ApplicationTheme.colors.backgroundPrimary
            )
        )

        Text(
            text = stringResource(titleRes),
            color = ApplicationTheme.colors.contentText,
            style = ApplicationTheme.typography.title1,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(title) {
                    start.linkTo(icon.end)
                    centerVerticallyTo(parent)
                }
        )

        Switch(
            checked = isEnabled,
            onCheckedChange = {
                onToggle(it)
            },
            modifier = Modifier
                .padding(end = 16.dp)
                .constrainAs(switch) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                }
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 16.dp, end = 16.dp)
                .background(
                    color = ApplicationTheme.colors.backgroundPrimary
                        .copy(alpha = 0.3f)
                ).constrainAs(separator) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Composable
private fun ClickableItem(
    @DrawableRes
    iconRes: Int,
    @StringRes
    titleRes: Int,
    onClick: () -> Unit?
) {
    ConstraintLayout(modifier = Modifier
        .fillMaxWidth()
        .height(56.dp)
        .debouncedClickable {
            onClick.invoke()
        }
    ) {
        val (icon, title, chevron, separator) = createRefs()

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(20.dp)
                .constrainAs(icon) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                },
            colorFilter = ColorFilter.tint(
                color = ApplicationTheme.colors.backgroundPrimary
            )
        )

        Text(
            text = stringResource(titleRes),
            color = ApplicationTheme.colors.contentText,
            style = ApplicationTheme.typography.title1,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 16.dp)
                .constrainAs(title) {
                    start.linkTo(icon.end)
                    centerVerticallyTo(parent)
                }
        )

        Image(
            painter = painterResource(id = R.drawable.ic_chevron),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .size(20.dp)
                .constrainAs(chevron) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                },
            colorFilter = ColorFilter.tint(
                color = ApplicationTheme.colors.backgroundPrimary
            )
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 16.dp, end = 16.dp)
                .background(
                    color = ApplicationTheme.colors.backgroundPrimary
                        .copy(alpha = 0.3f)
                ).constrainAs(separator) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenUiImplementation(
        viewState = SettingsScreenState(
            isVibrationEnabled = true
        )
    ) {
        //  here intents are being sent
    }
}