package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.domain.utils.monthToString
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.dropdown.SelectedDropDownElement
import java.time.LocalDate

@Composable
fun DatePeriod(modifier: Modifier = Modifier, startDate: LocalDate?, endDate: LocalDate?) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Text("Від:", style = FleetWisorTheme.typography.bodyLarge)
            Text("До:", style = FleetWisorTheme.typography.bodyLarge)
        }
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = (startDate?.dayOfMonth ?: "").toString()
                ) {}
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = startDate.monthToString()
                ) {}
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = (startDate?.year ?: "").toString()
                ) {}
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = (endDate?.dayOfMonth ?: "").toString()
                ) {}
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = endDate.monthToString()
                ) {}
                SelectedDropDownElement(
                    modifier = Modifier.weight(1f),
                    active = false,
                    textItem = (endDate?.year ?: "").toString()
                ) {}
            }
        }
    }
}

@Preview
@Composable
private fun DatePeriodPrev() {
    DatePeriod(
        startDate = LocalDate.now(),
        endDate = LocalDate.now()
    )
}