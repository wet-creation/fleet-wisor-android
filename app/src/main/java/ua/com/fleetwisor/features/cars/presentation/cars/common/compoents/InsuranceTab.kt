package ua.com.fleetwisor.features.cars.presentation.cars.common.compoents

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.domain.utils.toMillis
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.core.presentation.theme.components.images.BorderImage
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DatePeriod
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DateRangePickerModal
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateAction
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
inline fun <reified Action> InsuranceTab(
    insurance: Insurance,
    selectedPhoto: Uri?,
    crossinline onAction: (Action) -> Unit
) {

    val editMode =
        if (CarCreateAction is Action)
            false
        else if (CarEditAction is Action)
            true
        else return
    val pickPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            if (editMode) {
                onAction(CarEditAction.SelectPhoto(uri) as Action)

            } else {
                onAction(CarCreateAction.SelectPhoto(uri) as Action)
            }

        }
    }

    var showDateDialog by remember {
        mutableStateOf(false)
    }
    if (showDateDialog) {
        DateRangePickerModal(
            dateRangePickerState = rememberDateRangePickerState(
                initialSelectedStartDateMillis = insurance.startDate?.toMillis(),
                initialSelectedEndDateMillis = insurance.endDate?.toMillis(),
            ),
            onDateRangeSelected = {
                if (it.first != null && it.second != null) {
                    if (editMode) {
                        onAction(CarEditAction.SelectPeriod(it.first!!, it.second!!) as Action)

                    } else {
                        onAction(CarCreateAction.SelectPeriod(it.first!!, it.second!!) as Action)
                    }
                }

            }
        ) {
            showDateDialog = false
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(28.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(
                    stringResource(R.string.insurance_period_text) + ":",
                    color = FleetWisorTheme.colors.brandPrimaryLight,
                    style = FleetWisorTheme.typography.titleMedium
                )
                DatePeriod(
                    modifier = Modifier.clickable {
                        showDateDialog = true

                    },
                    startDate = insurance.startDate,
                    endDate = insurance.endDate
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    stringResource(R.string.document_text) + ":",
                    color = FleetWisorTheme.colors.brandPrimaryLight,
                    style = FleetWisorTheme.typography.titleMedium
                )
                if (selectedPhoto != null) {
                    BorderImage(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                pickPhoto.launch("image/*")
                            },
                        image = rememberAsyncImagePainter(selectedPhoto),
                        contentDescription = ""
                    )
                } else if (insurance.photoUrl.isNotEmptyOrBlank()) {
                    BorderImage(
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                pickPhoto.launch("image/*")
                            },
                        image = rememberAsyncImagePainter(insurance.photoUrl),
                        contentDescription = ""
                    )

                } else
                    SecondaryLongIconButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.add_document),
                        icon = FleetWisorTheme.icons.logout,
                        contentDescription = ""
                    ) {
                        pickPhoto.launch("image/*")
                    }
            }


        }

    }
}

@Preview
@Composable
private fun InsuranceTabPrev() {
    InsuranceTab(
        selectedPhoto = null,
        insurance = Insurance(
            photoUrl = ""
        )
    ) { it: CarCreateAction ->

    }
}