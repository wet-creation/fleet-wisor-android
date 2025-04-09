package ua.com.agroswit.theme.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme


@Composable
fun PropertyItem(modifier: Modifier = Modifier, propertyName: String, propertyValue: String) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        Text(
            text = propertyName,
            style = FleetWisorTheme.typography.titleMedium,
            color = FleetWisorTheme.colors.brandPrimaryMedium
        )
        Text(
            text = propertyValue,
            style = FleetWisorTheme.typography.bodyLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark
        )
    }
}