package ua.com.agroswit.theme.components.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SimpleTextFieldAgroswit(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    textAlign: TextAlign = TextAlign.Start,
    capitalization: Boolean = false,
    textStyle: TextStyle = FleetWisorTheme.typography.bodyLarge,
    imeAction: ImeAction = ImeAction.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (value.isEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = hint,
                textAlign = textAlign,
                color = FleetWisorTheme.colors.neutralSecondaryNormal,
                style = textStyle
            )
        }
        BasicTextField(
            enabled = enabled,
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = textStyle
                .copy(color = FleetWisorTheme.colors.neutralSecondaryDark,
                    textAlign = textAlign),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                capitalization = if (capitalization) KeyboardCapitalization.Sentences else KeyboardCapitalization.None
            ),
            keyboardActions = KeyboardActions( onDone = {
                focusManager.clearFocus()
            }),
            singleLine = singleLine,
            cursorBrush = SolidColor(FleetWisorTheme.colors.brandPrimaryNormal),
        )
    }
}

@Preview
@Composable
private fun Prev() {
    SimpleTextFieldAgroswit(value = "dfdsf", hint = "Ім’я", modifier = Modifier, textAlign = TextAlign.Start) {

    }
}