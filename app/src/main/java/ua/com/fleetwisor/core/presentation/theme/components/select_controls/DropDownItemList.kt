package ua.com.agroswit.theme.components.select_controls

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.utils.AutoResizedText


data class DropDownItemState(
    val id: Int = 0,
    val text: String = ""
)
@Composable
fun DropDownItemList(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    dropDownItemState: DropDownItemState,
    onItemClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .clickable(onClick =  { onItemClick(dropDownItemState.id) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() })
    ) {
        AutoResizedText(
            text = dropDownItemState.text,
            color = if (isActive) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.neutralSecondaryDark,
            style = FleetWisorTheme.typography.labelLargeM
        )
    }
}

@Preview
@Composable
private fun DropDownItemListPrev() {
    val state = remember {
        mutableStateOf(true)
    }

    DropDownItemList(
        isActive = state.value,
        dropDownItemState = DropDownItemState(0,"5 л"),
    ) {
        state.value = !state.value
    }
}