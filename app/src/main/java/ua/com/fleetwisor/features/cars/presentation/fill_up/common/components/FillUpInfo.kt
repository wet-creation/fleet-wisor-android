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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.scaffold.modal_botton_sheet.AgroswitModalBottomSheet
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.formatTime
import ua.com.fleetwisor.core.domain.utils.toPriceString
import ua.com.fleetwisor.core.domain.utils.toVolumeString
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets.SearchElements
import ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.CarSelectionButton
import ua.com.fleetwisor.core.presentation.theme.components.dropdown.SelectedDropDown
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DateTimePickerDialog
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditAction

@Composable
inline fun <reified Action> FillUpInfo(
    fillUp: FillUp,
    cars: List<Car>,
    paddingValues: PaddingValues,
    crossinline onAction: (Action) -> Unit,
) {
    val context = LocalContext.current

    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var showDateTimePicker by remember {
        mutableStateOf(false)
    }
    var searchValue by remember {
        mutableStateOf("")
    }
    val modalSheetBottom = remember {
        derivedStateOf {
            paddingValues.calculateBottomPadding() / 2
        }
    }
    var filteredCars by remember(cars) {
        mutableStateOf(cars)
    }
    val editMode = if (FillUpCreateAction is Action) false
    else if (FillUpEditAction is Action) true
    else return
    if (showDateTimePicker)
        DateTimePickerDialog(
            initialDateTime = fillUp.time,
            onDismissRequest = {
                showDateTimePicker = false
            }
        ) {
            showDateTimePicker = false

            if (editMode) {

            } else {
                onAction(FillUpCreateAction.SelectTimeDate(it) as Action)

            }
        }

    AgroswitModalBottomSheet(
        isVisible = showBottomSheet,
        onDismissRequest = {
            showBottomSheet = false
        },
    ) {
        SearchElements(
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 20.dp)
                .padding(bottom = modalSheetBottom.value),
            title = stringResource(R.string.cars_text),
            currentIndex = cars.indexOf(fillUp.car),
            inputValue = searchValue,
            elementsList = {
                filteredCars.map { it.name }
            },
            inputSearch = { value ->
                searchValue = value
                filteredCars = cars.filter { it.name.contains(value, true) }
            }) {
            showBottomSheet = false
            if (editMode) {

            } else {

                onAction(FillUpCreateAction.SelectedCarIndex(it) as Action)
            }
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
            CarSelectionButton(
                text = fillUp.car.name,
            ) {
                showBottomSheet = true
            }
            SelectedDropDown(
                selectedItem = fillUp.fuelType.id,
                displayedItemsCount = fillUp.car.fuelTypes.size + 1,
                overlapping = true,
                items = {
                    fillUp.car.fuelTypes.map {
                        DropDownItemState(
                            id = it.id, text = it.name
                        )
                    }
                }) {
                if (editMode) {

                } else {
                    onAction(FillUpCreateAction.SelectFuelType(it) as Action)
                }
            }
            HorizontalDivider()
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TitledLabelTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    icon = FleetWisorTheme.icons.calendar,
                    text = fillUp.time.formatTime(),
                    placeholder = "",
                    titleText = stringResource(R.string.fill_up_date_text) + "*",
                ) {
                    showDateTimePicker = true
                }
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.paid,
                    text = fillUp.price.toPriceString(),
                    unitText = stringResource(R.string.currency_uah_text),
                    placeholder = "1290",
                    titleText = stringResource(R.string.sum_text) + "*",
                ) {
                    if (editMode) {

                    } else {
                        onAction(FillUpCreateAction.InputPrice(it) as Action)
                    }
                }
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.gasMeter,
                    text = fillUp.amount.toVolumeString(),
                    placeholder = "",
                    unitText = fillUp.fuelUnits.name,
                    titleText = stringResource(R.string.fill_up_volume_text) + "*",
                ) {
                    if (editMode) {

                    } else {
                        onAction(FillUpCreateAction.InputAmount(it) as Action)
                    }
                }

            }

            if (editMode) {
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
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
                ) {
                    onAction(FillUpCreateAction.Save(context) as Action)

                }
            }
        }
    }
}

@Preview
@Composable
private fun FillUpInfoPrev() {
    FillUpInfo<FillUpEditAction>(
        fillUp = FillUp(),
        cars = emptyList(),
        paddingValues = PaddingValues(),
        onAction = {},
    )
}