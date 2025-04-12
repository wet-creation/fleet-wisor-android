package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ua.com.agroswit.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction

@Composable
inline fun <reified Action> CarInfoTab(
    car: Car,
    fuelTypes: ImmutableList<FuelType>,
    carBodies: ImmutableList<CarBody>,
    crossinline onAction: (Action) -> Unit,
) {
    val editMode = if (CarCreateAction is Action) false
    else if (CarEditAction is Action) true
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
            Text(
                stringResource(R.string.personal_data_car_text),
                color = FleetWisorTheme.colors.brandPrimaryMedium,
                style = FleetWisorTheme.typography.headlineMedium
            )
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.car,
                    text = car.brandName,
                    placeholder = "100",
                    titleText = stringResource(R.string.brand_name) + "*",
                    onChange = {})
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.gasFluid,
                    text = car.color ?: "",
                    placeholder = "100",
                    titleText = stringResource(R.string.car_color_text),
                    onChange = {})
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.tool,
                    text = car.model ?: "",
                    placeholder = "100",
                    titleText = stringResource(R.string.car_model),
                    onChange = {})
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.odometer,
                    text = "",
                    placeholder = "100",
                    titleText = stringResource(R.string.odometr_text) + "*",
                    onChange = {})

            }
            HorizontalDivider()
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                stringResource(R.string.specs_text),
                color = FleetWisorTheme.colors.brandPrimaryMedium,
                style = FleetWisorTheme.typography.headlineMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                LabelIconList(
                    modifier = Modifier.fillMaxSize(0.5f),
                    titleText = stringResource(R.string.car_body_text),
                    icon = FleetWisorTheme.icons.homeService,
                    selectedItemIndex = 0,
                    items = {
                        carBodies.map {
                            DropDownItemState(
                                it.id,
                                it.name
                            )
                        }
                    }
                ) { }
                LabelIconList(
                    modifier = Modifier.fillMaxSize(0.5f),
                    titleText = stringResource(R.string.fuel_type_text),
                    icon = FleetWisorTheme.icons.gasStation,
                    selectedItemIndex = 0,
                    items = {
                        fuelTypes.map {
                            DropDownItemState(
                                it.id,
                                it.name
                            )
                        }
                    }
                ) { }
            }
            HorizontalDivider()
        }


        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                stringResource(R.string.register_text),
                color = FleetWisorTheme.colors.brandPrimaryMedium,
                style = FleetWisorTheme.typography.headlineMedium
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    icon = FleetWisorTheme.icons.tool,
                    text = car.model ?: "",
                    placeholder = "1VWAH7A31CC102891",
                    titleText = stringResource(R.string.vin_number),
                    onChange = {})
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.numbers,
                    text = car.licensePlate,
                    placeholder = "КА 2387 СТ",
                    titleText = stringResource(R.string.car_number_text) + "*",
                    onChange = {})
            }
        }

        if (car.drivers.isEmpty()) {
            SecondaryNormalButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp, horizontal = 32.dp),
                text = stringResource(R.string.add_driver)
            ) { }
        }
        else {
            //todo добавить строку с водителями
        }

        if (editMode) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                PrimaryButton(
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
                    text = stringResource(R.string.save_text)
                ) {

                }
                SecondaryOnlyIconButton(
                    icon = FleetWisorTheme.icons.delete,
                    contentDescription = "delete"
                ) { }
            }
        } else {
            PrimaryButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
                text = stringResource(R.string.save_text)
            ) {

            }
        }

    }


}

@Preview
@Composable
private fun CarInfoTabPrev() {
    CarInfoTab<CarCreateAction>(
        car = Car(), onAction = {},
        fuelTypes = listOf(
            FuelType(name = "121"),
            FuelType(name = "234"),
        ).toImmutableList(),
        carBodies = listOf(
            CarBody(name = "121"),
            CarBody(name = "234"),
        ).toImmutableList(),
    )
}