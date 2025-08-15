package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType.Companion.PrimaryNotEditable
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.zigis.paleontologas.core.extensions.activeLocale
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.rememberDebouncedClick
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.quiz.R
import com.zigis.paleontologas.features.quiz.utilities.QuizMarkUtility
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaywallSheet(
    onDismiss: () -> Unit,
    onSubmit: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(
                R.raw.paywall
            ))
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.height(180.dp)
            )

            Text(
                text = stringResource(R.string.paywall_sheet_title),
                style = ApplicationTheme.typography.headline1,
                color = ApplicationTheme.colors.contentText,
                modifier = Modifier
                    .padding(top = 20.dp)
            )

            Text(
                text = stringResource(R.string.paywall_sheet_description),
                style = ApplicationTheme.typography.caption1,
                color = ApplicationTheme.colors.contentText.copy(alpha = 0.7f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 20.dp)
            )

            val debouncedClick = rememberDebouncedClick()

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 14.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ApplicationTheme.colors.backgroundPrimary,
                    contentColor = ApplicationTheme.colors.contentBackground
                ),
                onClick = {
                    debouncedClick {
                        onSubmit.invoke()
                    }
                }
            ) {
                Text(stringResource(id = R.string.continue_further))
            }
        }
    }
}
