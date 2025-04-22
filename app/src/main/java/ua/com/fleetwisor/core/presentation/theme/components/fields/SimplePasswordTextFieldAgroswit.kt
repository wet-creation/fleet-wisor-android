package ua.com.fleetwisor.core.presentation.theme.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SimplePasswordTextFieldAgroswit(
    modifier: Modifier = Modifier,
    value: String,
    isPasswordVisible: Boolean,
    hint: String,
    error: String? = null,
    imeAction: ImeAction = ImeAction.Default,
    onValueChange: (String) -> Unit,
    content: (@Composable () -> Unit)? = null,
    onTogglePasswordVisibility: () -> Unit
){
    var isFocused by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
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
            modifier = Modifier
                .height(40.dp)
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
                        if (value.isEmpty()) {
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
            } else if (content != null && isFocused) content()
        }
    }
}

@Preview
@Composable
private fun AuthPasswordTextFieldPreview() {
    FleetWisorTheme {
        SimplePasswordTextFieldAgroswit(
            value = "",
            onValueChange = {},
            hint = "example@test.com",
            error = "Обидва паролі мають збігатися",
            isPasswordVisible = false,
            onTogglePasswordVisibility = {},
        )
    }
}

