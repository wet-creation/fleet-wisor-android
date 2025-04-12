package ua.com.fleetwisor.features.cars.presentation.fill_up.common.components

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
import ua.com.agroswit.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditAction

@Composable
inline fun <reified Action> FillUpCheckTab(
    fillUp: FillUp,
    crossinline onAction: (Action) -> Unit,
) {
    val editMode =
        if (FillUpCreateAction is Action)
            false
        else if (FillUpEditAction is Action)
            true
        else return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        if (fillUp.checkUrl == null) {
            SecondaryLongIconButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.add_document),
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
                        fillUp.checkUrl,
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
    FillUpCheckTab<FillUpCreateAction>(
        fillUp = FillUp(),
        onAction = {}
    )
}