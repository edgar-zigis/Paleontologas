package com.zigis.paleontologas.features.quiz.stories.progress.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zigis.paleontologas.core.extensions.rememberDebouncedClick
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.core.ui.theme.ThemeColors
import com.zigis.paleontologas.features.quiz.R
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateUsernameBottomSheet(
    isLoading: Boolean,
    errorDescription: String,
    usernameText: String,
    activeCountryCode: String,
    allCountries: List<Locale>,
    onUsernameChange: (String) -> Unit,
    onClearUsername: () -> Unit,
    onSetUsername: (username: String, countryCode: String) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { false }
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
            if (isLoading) {
                CircularProgressIndicator(
                    color = Color.Gray,
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(vertical = 16.dp)
                )
            } else {
                Text(
                    text = stringResource(R.string.leaderboard_modal_claim_username_title),
                    style = ApplicationTheme.typography.headline1,
                    color = ApplicationTheme.colors.contentText,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Text(
                    text = stringResource(R.string.leaderboard_modal_claim_username_description),
                    style = ApplicationTheme.typography.caption1,
                    color = ApplicationTheme.colors.contentText.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 2.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 10.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    OutlinedTextField(
                        value = usernameText,
                        onValueChange = {
                            val newText = if (it.length <= 15) it else it.take(15)
                            onUsernameChange(newText)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.leaderboard_modal_claim_username_textfield_placeholder),
                                color = Color.Gray.copy(alpha = 0.6f),
                                style = ApplicationTheme.typography.content
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(12.dp)),
                        textStyle = ApplicationTheme.typography.content.copy(color = ApplicationTheme.colors.contentText),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            cursorColor = ApplicationTheme.colors.contentText
                        ),
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true
                    )

                    if (usernameText.isNotEmpty()) {
                        IconButton(
                            onClick = { onClearUsername() },
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Clear",
                                tint = ApplicationTheme.colors.contentText
                            )
                        }
                    }
                }

                if (errorDescription.isNotEmpty()) {
                    Text(
                        text = errorDescription,
                        style = ApplicationTheme.typography.caption2,
                        color = ThemeColors.Failure,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                }

                var selectedCountryCode by remember { mutableStateOf(activeCountryCode) }

                ExposedDropdownMenuBox(
                    expanded = false, // could be controlled if needed
                    onExpandedChange = {}
                ) {
                    val locale = Locale.Builder().setRegion(selectedCountryCode).build()
                    val localizedRegionName = locale.getDisplayCountry(Locale.getDefault())

                    OutlinedTextField(
                        readOnly = true,
                        value = localizedRegionName,
                        onValueChange = {},
                        label = { Text("") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray.copy(alpha = 0.2f), shape = RoundedCornerShape(8.dp)),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(false) },
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            cursorColor = ApplicationTheme.colors.contentText
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = {}
                    ) {
                        allCountries.forEach { region ->
                            val name = Locale
                                .getDefault()
                                .getDisplayCountry(Locale("", region.country)) ?: return@forEach
                            DropdownMenuItem(
                                text = { Text(name) },
                                onClick = {
                                    selectedCountryCode = region.country
                                }
                            )
                        }
                    }
                }

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
                            onSetUsername(usernameText, selectedCountryCode)
                        }
                    }
                ) {
                    Text(stringResource(id = R.string.continue_further))
                }
            }
        }
    }
}
