package ua.com.fleetwisor.core.presentation.theme.components.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    error: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    additionalInfo: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    isPhoneNumber: Boolean = false,
    characterLimit: Int? = null
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = if (error != null || isError) {
                        FleetWisorTheme.colors.errorDark
                    } else {
                        FleetWisorTheme.colors.neutralSecondaryDark
                    },
                    style = FleetWisorTheme.typography.labelLargeM
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = value,
            onValueChange = {
                if (characterLimit != null) {
                    if (it.length <= characterLimit) onValueChange(it)
                } else onValueChange(it)
            },
            textStyle = FleetWisorTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = true,
            cursorBrush = SolidColor(FleetWisorTheme.colors.brandPrimaryNormal),
            modifier = Modifier
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (error != null || isError) {
                        FleetWisorTheme.colors.errorLight
                    } else {
                        FleetWisorTheme.colors.neutralPrimaryLight
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (error != null || isError) {
                        FleetWisorTheme.colors.errorNormal
                    } else if (isFocused) {
                        FleetWisorTheme.colors.brandPrimaryNormal
                    } else {
                        FleetWisorTheme.colors.neutralSecondaryNormal
                    },
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(12.dp)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorationBox = { innerBox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isPhoneNumber && (isFocused || value.isNotBlank())) {
                        Text(text = "+380 ")
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        if (value.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                color = FleetWisorTheme.colors.neutralSecondaryNormal,
                                style = FleetWisorTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (error != null) {
                Text(
                    text = error,
                    color = FleetWisorTheme.colors.errorDark,
                    style = FleetWisorTheme.typography.bodyMedium
                )
            } else if (additionalInfo != null && isFocused) {
                Text(
                    text = additionalInfo,
                    color = FleetWisorTheme.colors.neutralSecondaryMedium,
                    style = FleetWisorTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun RuniqueTextFieldPreview() {
        AuthTextField(
            value = "",
            isPhoneNumber = true,
            onValueChange = {},
            hint = "example@test.com",
            title = "Email",
            additionalInfo = "Must be a valid email",
            error = null,
            modifier = Modifier
                .fillMaxWidth()
        )
}