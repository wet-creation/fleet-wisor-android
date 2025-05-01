package ua.com.agroswit.theme.components.fields


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme


@Composable
fun TextFieldAgroswit(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    hideKeyboard: Boolean = false,
    icon: Painter? = null,
    imeAction: ImeAction = ImeAction.Default,
    tint: Color = FleetWisorTheme.colors.brandPrimaryNormal,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(38.dp)
            .fillMaxWidth()
            .background(
                color = FleetWisorTheme.colors.neutralPrimaryLight,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .border(
                width = 1.dp,
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .padding(horizontal = 12.dp)

    ) {
        if (icon != null)
            Icon(
                painter = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.padding(end = 10.dp)
            )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            if (value.isEmpty()) {
                Text(
                    text = hint,
                    color = FleetWisorTheme.colors.neutralSecondaryNormal,
                    style = FleetWisorTheme.typography.labelLargeR,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = FleetWisorTheme.typography.labelLargeR
                    .copy(color = FleetWisorTheme.colors.neutralSecondaryDark),
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                singleLine = true,
                cursorBrush = SolidColor(FleetWisorTheme.colors.brandPrimaryNormal),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
            )
        }
        if (hideKeyboard) {
            focusManager.clearFocus()
        }
    }
}






@Preview
@Composable
private fun SearchNoFilterPreview() {
    val value = remember {
        mutableStateOf("")
    }
    TextFieldAgroswit(
        value = value.value,
        hint = "Пошук продукту",
        icon = FleetWisorTheme.icons.search
    ) {
        value.value = it
    }
}