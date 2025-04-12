package ua.com.fleetwisor.features.cars.presentation.maintenance.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import ua.com.fleetwisor.features.cars.domain.models.Maintenance
import ua.com.fleetwisor.features.cars.presentation.maintenance.create.MaintenanceCreateAction
import ua.com.fleetwisor.features.cars.presentation.maintenance.edit.MaintenanceEditAction

@Composable
inline fun <reified Action> MaintenanceCheckTab(
    maintenance: Maintenance,
    crossinline onAction: (Action) -> Unit,
) {
    val editMode =
        if (MaintenanceCreateAction is Action)
            false
        else if (MaintenanceEditAction is Action)
            true
        else return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        if (maintenance.checkUrl == null) {
            SecondaryLongIconButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add_check),
                icon = FleetWisorTheme.icons.logout,
                contentDescription = ""
            ) { }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    stringResource(R.string.fill_up_check_text) + ":",
                    color = FleetWisorTheme.colors.brandPrimaryLight,
                    style = FleetWisorTheme.typography.titleMedium
                )
                Image(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = FleetWisorTheme.colors.neutralSecondaryDark,
                            shape = RoundedCornerShape(size = 5.dp)
                        ),
                    painter = rememberAsyncImagePainter(
                        maintenance.checkUrl,
                        //todo replace placeholder
                        placeholder = R.drawable.product_placeholder
                    ), contentDescription = ""
                )
            }
        }
    }
}

@Preview
@Composable
private fun FillUpCheckTabPrev() {
    MaintenanceCheckTab<MaintenanceCreateAction>(
        maintenance = Maintenance(),
        onAction = {}
    )
}