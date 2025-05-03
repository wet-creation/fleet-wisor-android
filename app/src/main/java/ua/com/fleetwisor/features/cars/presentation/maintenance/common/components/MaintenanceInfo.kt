package ua.com.fleetwisor.features.cars.presentation.maintenance.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.scaffold.modal_botton_sheet.AgroswitModalBottomSheet
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.formatTime
import ua.com.fleetwisor.core.domain.utils.isDouble
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets.SearchElements
import ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.CarSelectionButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.SimpleTextFieldAgroswit
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DateTimePickerDialog
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.Maintenance
import ua.com.fleetwisor.features.cars.presentation.maintenance.create.MaintenanceCreateAction
import ua.com.fleetwisor.features.cars.presentation.maintenance.edit.MaintenanceEditAction

@Composable
inline fun <reified Action> MaintenanceInfo(
    maintenance: Maintenance,
    cars: List<Car>,
    paddingValues: PaddingValues,
    crossinline onAction: (Action) -> Unit,
) {
    val context = LocalContext.current
    val editMode = if (MaintenanceCreateAction is Action) false
    else if (MaintenanceEditAction is Action) true
    else return
    var showDateTimePicker by remember {
        mutableStateOf(false)
    }
    var inputPrice by remember(maintenance.price) {
        mutableStateOf(maintenance.price.toString())
    }
    val modalSheetBottom = remember {
        derivedStateOf {
            paddingValues.calculateBottomPadding() / 2
        }
    }
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var searchValue by remember {
        mutableStateOf("")
    }
    var filteredCars by remember(cars) {
        mutableStateOf(cars)
    }
    if (showDateTimePicker)
        DateTimePickerDialog(
            initialDateTime = maintenance.time,
            onDismissRequest = {
                showDateTimePicker = false
            }
        ) {
            showDateTimePicker = false

            if (editMode) {
                onAction(MaintenanceEditAction.SelectTimeDate(it) as Action)

            } else {
                onAction(MaintenanceCreateAction.SelectTimeDate(it) as Action)

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
            currentIndex = cars.indexOf(maintenance.car),
            inputValue = searchValue,
            elementsList = {
                filteredCars.map { it.name }
            },
            inputSearch = { value ->
                searchValue = value
                filteredCars = cars.filter { it.name.contains(value, true) }
            }) {
            showBottomSheet = false
            if (!editMode) {
                onAction(MaintenanceCreateAction.SelectedCarIndex(it) as Action)
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
                enabled = !editMode,
                text = maintenance.car.name,
            ) {
                showBottomSheet = true
            }
            HorizontalDivider()
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TitledLabelTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    icon = FleetWisorTheme.icons.calendar,
                    text = maintenance.time.formatTime(),
                    placeholder = "",
                    titleText = stringResource(R.string.fill_up_date_text) + "*",
                ) {
                    showDateTimePicker = true
                }
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.paid,
                    text = inputPrice,
                    unitText = stringResource(R.string.currency_uah_text),
                    placeholder = "1223",
                    titleText = stringResource(R.string.sum_text) + "*",
                ) {
                    if (it.isDouble()) {
                        inputPrice = it
                        if (editMode) {
                            onAction(
                                MaintenanceEditAction.InputPrice(
                                    it.toDoubleOrNull() ?: 0.0
                                ) as Action
                            )

                        } else {
                            onAction(
                                MaintenanceCreateAction.InputPrice(
                                    it.toDoubleOrNull() ?: 0.0
                                ) as Action
                            )
                        }
                    }
                }

                Column {
                    Text(
                        text = "${stringResource(R.string.description)}*:",
                        style = FleetWisorTheme.typography.titleMedium,
                        color = FleetWisorTheme.colors.brandPrimaryLight
                    )
                    SimpleTextFieldAgroswit(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                1.dp,
                                FleetWisorTheme.colors.brandPrimaryMedium,
                                RoundedCornerShape(5.dp)
                            )
                            .background(
                                color = FleetWisorTheme.colors.neutralPrimaryLight,
                                shape =
                                    RoundedCornerShape(5.dp)
                            )
                            .padding(vertical = 4.dp, horizontal = 8.dp)
                            .heightIn(min = 120.dp),

                        singleLine = false,
                        value = maintenance.description,
                        hint = stringResource(R.string.maintenance_description_hint),
                        onValueChange = {
                            if (editMode) {
                                onAction(MaintenanceEditAction.InputDescription(it) as Action)

                            } else
                                onAction(MaintenanceCreateAction.InputDescription(it) as Action)

                        }
                    )
                }
            }

            if (editMode) {
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PrimaryButton(
                        enabled = maintenance.description.isNotEmptyOrBlank(),
                        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 24.dp),
                        text = stringResource(R.string.save_text)
                    ) {
                        onAction(MaintenanceEditAction.Save(context) as Action)

                    }
                    SecondaryOnlyIconButton(
                        modifier = Modifier.size(52.dp),
                        tint = FleetWisorTheme.colors.errorDark,
                        icon = FleetWisorTheme.icons.delete,
                        contentDescription = "delete"
                    ) {
                        onAction(MaintenanceEditAction.Delete as Action)

                    }
                }
            } else {
                PrimaryButton(
                    enabled = maintenance.description.isNotEmptyOrBlank(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.save_text),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                ) {

                    onAction(MaintenanceCreateAction.Save(context) as Action)

                }
            }
        }
    }
}

@Preview
@Composable
private fun MaintenanceIInfoPrev() {
    MaintenanceInfo<MaintenanceCreateAction>(
        maintenance = Maintenance(),
        cars = emptyList(),
        paddingValues = PaddingValues(),
        onAction = {}
    )
}