package ua.com.agroswit.theme.components.fields


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.only_icon.PrimaryOnlyIconButton


@Composable
fun OutsideButtonTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    imeAction: ImeAction = ImeAction.Default,
    iconStart: Painter? = null,
    iconButton: Painter,
    tint: Color = FleetWisorTheme.colors.brandPrimaryNormal,
    keyboardType: KeyboardType = KeyboardType.Text,
    onClickRightIcon: () -> Unit = {},
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextFieldAgroswit(
            value = value,
            hint = hint,
            icon = iconStart,
            imeAction = imeAction,
            tint = tint,
            keyboardType = keyboardType,
            modifier = Modifier.weight(1f),
            onValueChange = onValueChange
        )
        PrimaryOnlyIconButton(
            icon = iconButton,
            contentDescription = null,
            tint = FleetWisorTheme.colors.brandSecondaryNormal,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.2f),
            onClick = onClickRightIcon
        )
    }

}


@Preview
@Composable
private fun TextFieldWithButtonPreview() {
    val active = remember {
        mutableStateOf(true)
    }
    val value = remember {
        mutableStateOf("")
    }

    OutsideButtonTextField(
        value = value.value,
        hint = "Пошук продукту",
        iconStart = FleetWisorTheme.icons.search,
        iconButton = if (active.value) FleetWisorTheme.icons.filterSelected else FleetWisorTheme.icons.filterUnSelected,
        onClickRightIcon = {
            active.value = !active.value
        }
    ) {
        value.value = it
    }
}