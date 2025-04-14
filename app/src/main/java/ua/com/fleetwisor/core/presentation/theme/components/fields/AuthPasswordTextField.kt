package ua.com.fleetwisor.core.presentation.theme.components.fields

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AuthPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier,
    error: String? = null,
    additionalInfo: String? = null,
    content: (@Composable () -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Default
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
                    color = if (!error.isNullOrBlank()) {
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
            onValueChange = onValueChange,
            visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            textStyle = FleetWisorTheme.typography.bodyLarge,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = imeAction
            ),
            cursorBrush = SolidColor(FleetWisorTheme.colors.brandPrimaryNormal),
            modifier = Modifier
                .height(40.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    if (!error.isNullOrBlank()) {
                        FleetWisorTheme.colors.errorLight
                    } else {
                        FleetWisorTheme.colors.neutralPrimaryLight
                    }
                )
                .border(
                    width = 1.dp,
                    color = if (!error.isNullOrBlank()) {
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

                    Icon(
                        painter = if (!isPasswordVisible) {
                            FleetWisorTheme.icons.eyeClose
                        } else FleetWisorTheme.icons.eyeOpen,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable { onTogglePasswordVisibility() }
                    )


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
            } else if (additionalInfo != null) {
                Text(
                    text = additionalInfo,
                    color = FleetWisorTheme.colors.neutralSecondaryMedium,
                    style = FleetWisorTheme.typography.bodyMedium
                )
            } else if (content != null && isFocused) content()
        }
    }
}

@Preview
@Composable
private fun AuthPasswordTextFieldPreview() {
    FleetWisorTheme {
        AuthPasswordTextField(
            value = "",
            onValueChange = {},
            hint = "example@test.com",
            title = "Email",
            additionalInfo = "Must be a valid email",
            error = null,
            isPasswordVisible = false,
            onTogglePasswordVisibility = {}

        )
    }
}