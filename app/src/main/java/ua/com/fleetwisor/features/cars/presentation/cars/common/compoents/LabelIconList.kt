package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.dropdown.SelectedDropDown
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.core.domain.utils.Index
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun LabelIconList(
    modifier: Modifier = Modifier,
    titleText: String,
    icon: Painter,
    selectedItemIndex: Index,
    items: () -> List<DropDownItemState>,
    onItemSelected: (Int) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "$titleText:",
            style = FleetWisorTheme.typography.titleMedium,
            color = FleetWisorTheme.colors.brandPrimaryLight
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                tint = FleetWisorTheme.colors.brandPrimaryNormal,
                contentDescription = ""
            )
            SelectedDropDown(
                modifier = Modifier,
                selectedItemIndex = selectedItemIndex,
                displayedItemsCount = 5,
                items = items,
                onItemChange = onItemSelected
            )
        }
    }

}

@Preview
@Composable
private fun LabelIconListPrev() {
    LabelIconList(
        titleText = "Hello",
        icon = FleetWisorTheme.icons.tool,
        selectedItemIndex = 0,
        items = {
            listOf(
                DropDownItemState(
                    id = -1,
                    text = "BABa"
                ),
                DropDownItemState(
                    id = -1,
                    text = "BABa"
                ),
                DropDownItemState(
                    id = -1,
                    text = "BABa"
                ),
                DropDownItemState(
                    id = -1,
                    text = "BABa"
                ),
            )
        },
        onItemSelected = {}
    )
}