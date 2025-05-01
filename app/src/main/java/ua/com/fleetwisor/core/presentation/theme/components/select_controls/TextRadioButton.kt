package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.UiText


data class TextRadioState(
    val text: UiText,
    val checked: Boolean = false,
)

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    state: TextRadioState,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                onCheckedChange()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            colors = RadioButtonDefaults.colors()
                .copy(
                    selectedColor = FleetWisorTheme.colors.brandPrimaryNormal,
                    unselectedColor = FleetWisorTheme.colors.neutralSecondaryNormal,
                ),
            selected = state.checked,
            onClick = null
        )
        Text(
            text = state.text.asString(),
            style = FleetWisorTheme.typography.labelLargeR,
            color = FleetWisorTheme.colors.neutralSecondaryDark,
        )

    }
}

@Preview
@Composable
private fun Prev() {
    val state = remember {
        mutableStateOf(TextRadioState(text = UiText.DynamicString("Bayer")))
    }
    TextRadioButton(state = state.value, onCheckedChange = {
        state.value = state.value.copy(checked = !state.value.checked)
    })
}