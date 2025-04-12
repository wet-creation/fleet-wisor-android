package ua.com.fleetwisor.features.cars.presentation.fill_up.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.CarSelectionButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditAction

@Composable
inline fun <reified Action> FillUpInfo(
    fillUp: FillUp,
    crossinline onAction: (Action) -> Unit,
) {
    val carName by remember {
        derivedStateOf {
            "${fillUp.car.color ?: ""} ${fillUp.car.brandName} ${fillUp.car.model ?: ""}"
        }
    }
    val editMode = if (FillUpCreateAction is Action) false
    else if (FillUpEditAction is Action) true
    else return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CarSelectionButton(
                text = carName,
            ) { }
            HorizontalDivider()
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TitledLabelTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    icon = FleetWisorTheme.icons.calendar,
                    text = fillUp.time,
                    placeholder = "",
                    titleText = stringResource(R.string.fill_up_date_text) + "*",
                ) { }
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.paid,
                    text = fillUp.time,
                    unitText = stringResource(R.string.currency_uah_text),
                    placeholder = "1290",
                    titleText = stringResource(R.string.sum_text) + "*",
                ) { }
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(),
                    icon = FleetWisorTheme.icons.gasMeter,
                    text = fillUp.amount.toString(),
                    placeholder = "",
                    titleText = stringResource(R.string.fill_up_volume_text) + "*",
                ) { }
            }

            if (editMode) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    PrimaryButton(
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
                        text = stringResource(R.string.save_text)
                    ) {

                    }
                    SecondaryOnlyIconButton(
                        modifier = Modifier.size(52.dp),
                        tint = FleetWisorTheme.colors.errorDark,
                        icon = FleetWisorTheme.icons.delete,
                        contentDescription = "delete"
                    ) { }
                }
            } else {
                PrimaryButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.save_text),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                ) { }
            }
        }
    }
}

@Preview
@Composable
private fun FillUpInfoPrev() {
    FillUpInfo<FillUpEditAction>(
        fillUp = FillUp(),
        onAction = {}
    )
}