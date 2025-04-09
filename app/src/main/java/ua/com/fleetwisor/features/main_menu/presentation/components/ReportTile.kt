package ua.com.fleetwisor.features.main_menu.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun ReportTile(
    modifier: Modifier = Modifier,
    reportText: String,
    value: String,
    unit: String,
    icon: Painter,
    iconColor: Color,
) {
    Column(
        modifier = modifier
            .border(
                2.dp,
                color = FleetWisorTheme.colors.neutralSecondaryDark,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(reportText, style = FleetWisorTheme.typography.labelMediumSB)
            Icon(painter = icon, contentDescription = reportText, tint = iconColor)
        }
        Text("$value $unit")
    }
}

@Preview
@Composable
private fun ReportTilePrev() {
    ReportTile(
        reportText = "Сума витрат",
        value = "7777",
        unit = "грн",
        icon = FleetWisorTheme.icons.money,
        iconColor = FleetWisorTheme.colors.brandSecondaryNormal,
    )
}