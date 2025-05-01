package ua.com.agroswit.theme.components.select_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme


data class TextCheckboxState(
    val id: Int = 0,
    val text: String,
    val checked: Boolean = false,
)

@Composable
fun AgroswitCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null
) {
    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides Dp.Unspecified) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            colors = CheckboxDefaults.colors()
                .copy(
                    checkedCheckmarkColor = FleetWisorTheme.colors.neutralPrimaryLight,
                    checkedBoxColor = FleetWisorTheme.colors.brandPrimaryNormal,
                    checkedBorderColor = FleetWisorTheme.colors.brandPrimaryNormal,
                    uncheckedBorderColor = FleetWisorTheme.colors.neutralSecondaryLight,
                ),
            interactionSource = interactionSource
        )
    }
}


@Composable
fun TextCheckbox(
    modifier: Modifier = Modifier,
    textCheckboxState: TextCheckboxState,
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
        AgroswitCheckbox(
            checked = textCheckboxState.checked,
            onCheckedChange = null
        )
        Text(
            text = textCheckboxState.text,
            style = FleetWisorTheme.typography.labelLargeR,
            color = FleetWisorTheme.colors.neutralSecondaryDark,
        )

    }
}

@Preview
@Composable
private fun TextCheckboxPrev() {
    val state = remember {
        mutableStateOf(TextCheckboxState(id = 9, text = "Bayer"))
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        TextCheckbox(
            textCheckboxState = state.value,
            onCheckedChange = {
                state.value = state.value.copy(checked = !state.value.checked)
            })

        AgroswitCheckbox(false, onCheckedChange = {})
    }
}
