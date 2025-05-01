package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.buttons.standart.ThirdAgroswitButton
import ua.com.agroswit.theme.components.scaffold.modal_botton_sheet.AgroswitModalBottomSheet
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.Index
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets.MultipleSearchElements
import ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.fleetwisor.core.presentation.theme.components.dropdown.SelectedDropDown
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction
import ua.com.fleetwisor.features.drivers.domain.models.Driver

@Composable
inline fun <reified Action> CarInfoTab(
    car: Car,
    canBeSaved: Boolean,
    paddingValue: PaddingValues,
    drivers: ImmutableList<Driver>,
    driverSearchValue: String,
    selectedDrivers: ImmutableList<Index>,
    fuelTypes: ImmutableList<FuelType>,
    carBodies: ImmutableList<CarBody>,
    crossinline onAction: (Action) -> Unit,
) {
    val context = LocalContext.current
    val editMode = if (CarCreateAction is Action) false
    else if (CarEditAction is Action) true
    else return
    val driversText by remember(selectedDrivers) {
        derivedStateOf {
            car.drivers.joinToString {
                it.fullName
            }
        }
    }
    val modalSheetBottom = remember {
        derivedStateOf {
            paddingValue.calculateBottomPadding() / 2
        }
    }
    var showModalSheet by remember { mutableStateOf(false) }
    AgroswitModalBottomSheet(
        isVisible = showModalSheet,
        onDismissRequest = {
            showModalSheet = false
        },
    ) {

        if (showModalSheet) {
            MultipleSearchElements(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 20.dp)
                    .padding(bottom = modalSheetBottom.value),
                selectedItems = { selectedDrivers },
                itemsList = { drivers.map { it.fullName } },
                inputSearchValue = driverSearchValue,
                inputSearch = {
                    if (editMode)
                    else {
                        onAction(CarCreateAction.SearchDriver(it) as Action)

                    }
                },
                onSave = {
                    if (editMode)
                    else {
                        onAction(CarCreateAction.SelectDriver(it) as Action)

                    }
                    showModalSheet = false
                }
            )
        }
    }
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
                    placeholder = "BMW",
                    titleText = stringResource(R.string.brand_name) + "*",
                    onChange = {
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputBrandName(it) as Action)

                        }
                    })
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.gasFluid,
                    text = car.color,
                    placeholder = stringResource(R.string.color_hint),
                    titleText = stringResource(R.string.car_color_text),
                    onChange = {
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputColor(it) as Action)
                        }
                    })
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.tool,
                    text = car.model,
                    placeholder = "X5",
                    titleText = stringResource(R.string.car_model),
                    onChange = {
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputModel(it) as Action)
                        }
                    })
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.odometer,
                    text = car.mileAge.toString(),
                    placeholder = "100",
                    titleText = stringResource(R.string.odometr_text) + "*",
                    onChange = {
                        if (it.toLongOrNull() == null)
                            return@TitledLabelTextField
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputOdometer(it) as Action)
                        }
                    })

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
                    selectedItem = car.carBody.id,
                    items = {
                        carBodies.map {
                            DropDownItemState(
                                it.id,
                                it.name
                            )
                        }
                    }
                ) {
                    if (editMode)
                    else {
                        onAction(CarCreateAction.SelectCarBody(it) as Action)
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.animateContentSize()
                ) {
                    Text(
                        text = stringResource(R.string.fuel_type_text),
                        style = FleetWisorTheme.typography.titleMedium,
                        color = FleetWisorTheme.colors.brandPrimaryLight
                    )
                    car.fuelTypes.forEachIndexed { index, fuelType ->
                        Row(
                            modifier = Modifier.fillMaxWidth(0.5f),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = FleetWisorTheme.icons.gasStation,
                                tint = FleetWisorTheme.colors.brandPrimaryNormal,
                                contentDescription = ""
                            )
                            SelectedDropDown(
                                modifier = Modifier.weight(0.9f),
                                displayedItemsCount = fuelTypes.size,
                                selectedItem = fuelType.id,
                                items = {
                                    fuelTypes.map {
                                        DropDownItemState(
                                            it.id,
                                            it.name
                                        )
                                    }
                                }
                            ) {
                                if (editMode)
                                else {
                                    onAction(CarCreateAction.SelectFuelType(index, it) as Action)
                                }
                            }
                            if (car.fuelTypes.size > 1) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            if (editMode) {

                                            } else
                                                onAction(CarCreateAction.DeleteFuelType(index) as Action)

                                        }
                                        .size(16.dp)
                                        .weight(0.1f),
                                    painter = FleetWisorTheme.icons.cross,
                                    tint = FleetWisorTheme.colors.neutralSecondaryLight,
                                    contentDescription = ""
                                )
                            }
                        }
                    }
                }
                ThirdAgroswitButton(
                    text = stringResource(R.string.add_text) + " +",
                    color = FleetWisorTheme.colors.brandPrimaryNormal
                ) {
                    if (editMode) {
                    } else {
                        onAction(CarCreateAction.AddFuelType as Action)
                    }
                }
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
                    text = car.vin,
                    placeholder = "1VWAH7A31CC102891",
                    titleText = stringResource(R.string.vin_number),
                    onChange = {
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputVin(it) as Action)

                        }
                    })
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.numbers,
                    text = car.licensePlate,
                    placeholder = "КА 2387 СТ",
                    titleText = stringResource(R.string.car_number_text) + "*",
                    onChange = {
                        if (editMode)
                        else {
                            onAction(CarCreateAction.InputLicensePlate(it) as Action)

                        }
                    })
            }
        }

        if (selectedDrivers.isEmpty()) {
            SecondaryNormalButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp, horizontal = 32.dp),
                text = stringResource(R.string.add_driver)
            ) {
                showModalSheet = true
            }
        } else {
            TitledLabelTextButton(
                icon = FleetWisorTheme.icons.person,
                titleText = stringResource(R.string.drivers_text),
                text = driversText,
                placeholder = stringResource(R.string.drivers_text),
                onClick = {
                    showModalSheet = true
                }
            )
        }

        if (editMode) {
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                PrimaryButton(
                    enabled = canBeSaved,
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
                enabled = canBeSaved,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
                text = stringResource(R.string.save_text)
            ) {
                onAction(CarCreateAction.SaveCar(context) as Action)

            }
        }

    }


}

@Preview
@Composable
private fun CarInfoTabPrev() {
    CarInfoTab<CarEditAction>(
        car = Car(
            drivers = listOf(
                Driver(name = "Buba", surname = "Zupa"),
                Driver(name = "Guba", surname = "Wuba"),
                Driver(name = "Nuba", surname = "Hoba"),
                Driver(name = "Nuba", surname = "Hoba"),
            )
        ),

        onAction = {},
        paddingValue = PaddingValues(),
        fuelTypes = listOf(
            FuelType(name = "121"),
            FuelType(name = "234"),
        ).toImmutableList(),
        carBodies = listOf(
            CarBody(name = "121"),
            CarBody(name = "234"),
        ).toImmutableList(),
        drivers = listOf<Driver>().toImmutableList(),
        driverSearchValue = "",
        canBeSaved = true,
        selectedDrivers = listOf<Int>().toImmutableList(),
    )
}