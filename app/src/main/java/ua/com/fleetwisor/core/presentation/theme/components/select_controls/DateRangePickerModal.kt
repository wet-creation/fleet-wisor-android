package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.agroswit.theme.components.buttons.standart.ThirdAgroswitButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerModal(
    dateRangePickerState: DateRangePickerState = rememberDateRangePickerState(),
    onDateRangeSelected: (Pair<Long?, Long?>) -> Unit,
    onDismiss: () -> Unit
) {
    DatePickerDialog(
        colors = DatePickerDefaults.colors(
            containerColor = FleetWisorTheme.colors.neutralPrimaryLight,
            titleContentColor = FleetWisorTheme.colors.brandPrimaryNormal,
            headlineContentColor = FleetWisorTheme.colors.brandPrimaryMedium,
            weekdayContentColor = FleetWisorTheme.colors.neutralSecondaryDark,
            subheadContentColor = FleetWisorTheme.colors.neutralSecondaryNormal,
            yearContentColor = FleetWisorTheme.colors.brandPrimaryNormal,
            selectedYearContainerColor = FleetWisorTheme.colors.brandPrimaryNormal,
            selectedYearContentColor = FleetWisorTheme.colors.neutralPrimaryLight,
            dayContentColor = FleetWisorTheme.colors.neutralSecondaryMedium,
            selectedDayContainerColor = FleetWisorTheme.colors.brandPrimaryNormal,
            selectedDayContentColor = FleetWisorTheme.colors.neutralPrimaryLight,
            todayContentColor = FleetWisorTheme.colors.brandSecondaryNormal,
            todayDateBorderColor = FleetWisorTheme.colors.brandSecondaryNormal,
            disabledDayContentColor = FleetWisorTheme.colors.neutralSecondaryLight
        ),
        onDismissRequest = onDismiss,
        confirmButton = {
            ThirdAgroswitButton(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                text = "OK",
                onClick = {
                    onDateRangeSelected(
                        Pair(
                            dateRangePickerState.selectedStartDateMillis,
                            dateRangePickerState.selectedEndDateMillis
                        )
                    )
                    onDismiss()
                }
            )
        },
        dismissButton = {
            ThirdAgroswitButton(
                modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                text = stringResource(R.string.cancel),
                onClick = {
                    onDismiss()
                }
            )
        }
    ) {
        DateRangePicker(
            state = dateRangePickerState,
            title = {

            },
            showModeToggle = false,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun DateRangePickerModalPrev() {
    DateRangePickerModal(
        onDateRangeSelected = {},
        onDismiss = {}
    )
}