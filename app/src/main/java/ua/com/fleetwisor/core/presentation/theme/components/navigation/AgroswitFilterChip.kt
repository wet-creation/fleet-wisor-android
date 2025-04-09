package ua.com.agroswit.theme.components.navigation

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitFilterChip(
    modifier: Modifier = Modifier,
    isActive: Boolean,
    enabled: Boolean = true,
    text: String,
    style: TextStyle = FleetWisorTheme.typography.labelLargeR,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = modifier,
        selected = isActive,
        enabled = enabled,
        shape = CircleShape,
        onClick = onClick,
        label = {
            Text(text = text, style = style)
        },
        colors = FilterChipDefaults.filterChipColors().copy(
            containerColor = Color.Transparent,
            labelColor = FleetWisorTheme.colors.neutralSecondaryDark,
            selectedLabelColor = FleetWisorTheme.colors.neutralPrimaryLight,
            selectedContainerColor = FleetWisorTheme.colors.brandPrimaryNormal,
            disabledSelectedContainerColor = FleetWisorTheme.colors.brandPrimaryNormal,
            disabledLabelColor = FleetWisorTheme.colors.neutralPrimaryLight
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = enabled, selected = isActive,
            borderColor = FleetWisorTheme.colors.brandPrimaryNormal,
            selectedBorderColor = FleetWisorTheme.colors.brandPrimaryNormal,
            disabledBorderColor = FleetWisorTheme.colors.brandPrimaryNormal,
            borderWidth = 1.dp,
            selectedBorderWidth = 0.dp,
        )
    )
}


@Preview
@Composable
private fun AgroswitChipPrev() {
    AgroswitFilterChip(isActive = true, text = "Новинка", enabled = false) {

    }
}