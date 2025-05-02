package ua.com.fleetwisor.core.presentation.theme.components.buttons.standart

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SelectionButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .border(
                1.dp,
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(horizontal = 6.dp, vertical = 8.dp)
            .then(if (enabled) Modifier.clickable(onClick = onClick) else Modifier)
    ) {
        Icon(
            painter = icon,
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
            contentDescription = ""
        )
        Text(
            text,
            style = FleetWisorTheme.typography.bodySmall,
            color = FleetWisorTheme.colors.brandPrimaryNormal
        )
        Icon(
            modifier = Modifier.size(16.dp),
            painter = FleetWisorTheme.icons.arrowDown,
            contentDescription = "",
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
        )
    }

}

@Composable
fun CarSelectionButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    SelectionButton(
        modifier = modifier,
        enabled = enabled,
        icon = FleetWisorTheme.icons.car,
        text = text,
        onClick = onClick
    )
}

@Preview
@Composable
private fun SelectionButtonPrev() {
    SelectionButton(
        icon = FleetWisorTheme.icons.car,
        text = "Чорна Toyota Camry",
        onClick = {}
    )
}