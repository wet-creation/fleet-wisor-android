package ua.com.agroswit.theme.components.validation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun PasswordRequirement(
    text: String,
    isValid: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = if (isValid) {
                FleetWisorTheme.icons.check
            } else {
                FleetWisorTheme.icons.cross
            },
            contentDescription = null,
            tint = if(isValid) FleetWisorTheme.colors.brandPrimaryMedium else FleetWisorTheme.colors.errorDark
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = if(isValid) FleetWisorTheme.colors.neutralSecondaryDark else FleetWisorTheme.colors.errorDark,
            style = FleetWisorTheme.typography.bodyMedium
        )
    }
}