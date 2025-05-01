package ua.com.fleetwisor.features.main_menu.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun ReportTile(
    modifier: Modifier = Modifier,
    reportText: String,
    value: String,
    unit: String = "",
    icon: Painter,
    iconColor: Color,
) {
    var iconSize by remember { mutableStateOf(0.dp) }
    val localDensity = LocalDensity.current
    Column(
        modifier = modifier
            .border(
                2.dp,
                color = FleetWisorTheme.colors.neutralSecondaryDark,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                reportText,
                style = FleetWisorTheme.typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = icon,
                contentDescription = reportText,
                tint = iconColor,
                modifier = Modifier
                    .weight(0.4f)
                    .onGloballyPositioned { coordinates ->
                        iconSize = with(localDensity) { coordinates.size.width.toDp() }
                    }
                    .height(iconSize))
        }
        Text("$value $unit", style = FleetWisorTheme.typography.titleMedium)
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