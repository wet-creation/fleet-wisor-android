package ua.com.agroswit.theme.components.dropdown

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.select_controls.TextCheckbox
import ua.com.agroswit.theme.components.select_controls.TextCheckboxState

@Composable
fun FilterUiGroup(
    modifier: Modifier = Modifier,
    name: String,
    filters: () -> List<TextCheckboxState>,
    onFilterClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(true) }
    Column(
        modifier = modifier
            .animateContentSize()
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clickable { expanded = !expanded }
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                style = FleetWisorTheme.typography.titleLarge,
                color = FleetWisorTheme.colors.neutralPrimaryLight
            )
            Icon(
                painter = if (expanded) FleetWisorTheme.icons.arrowUp else FleetWisorTheme.icons.arrowDown,
                contentDescription = null,
                tint = FleetWisorTheme.colors.neutralPrimaryLight,
                modifier = Modifier
                    .padding(1.dp)
                    .width(16.dp)
                    .height(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(FleetWisorTheme.dimensions.spacing2))
        if (expanded) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                filters().forEach { item ->
                    TextCheckbox(textCheckboxState = item, onCheckedChange = {
                        onFilterClick(item.id)
                    })
                }
                Spacer(modifier = Modifier)
            }
        }
    }
}

@Preview
@Composable
private fun FilterGroupPrev() {
    FilterUiGroup(name = "Діюча речовина", filters = {
        listOf(
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
            TextCheckboxState(0, "2-етилгексиловий ефір 2,4-Д"),
        )
    }) {

    }
}