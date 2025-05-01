package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import android.graphics.drawable.Icon
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.ktor.websocket.Frame.Text
import ua.com.agroswit.theme.components.buttons.standart.ThirdAgroswitButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.millisToLocalDate
import ua.com.fleetwisor.core.domain.utils.toMillis
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
object DatesUtilToday : SelectableDates {
    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return LocalDate.now().toMillis() >= utcTimeMillis
    }

    override fun isSelectableYear(year: Int): Boolean {
        return LocalDate.now().year >= year
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState(
        selectedDate.toMillis(),
        selectableDates = DatesUtilToday,
        yearRange = IntRange(1930, LocalDate.now().year)
    )

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
                    onDateSelected(datePickerState.selectedDateMillis?.millisToLocalDate())
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
        DatePicker(state = datePickerState)
    }
}
//
//@Composable
//fun DatePickerFieldToModal(
//    modifier: Modifier = Modifier,
//    selectedDate: LocalDate,
//    onDateSelected: (LocalDate?) -> Unit,
//) {
//    var showModal by remember { mutableStateOf(false) }
//
//    OutlinedTextField(
//        value = selectedDate.toString(),
//        onValueChange = { },
//        label = { Text("DOB") },
//        placeholder = { Text("DD.MM.YYYY") },
//        trailingIcon = {
//            FleetWisorTheme.icons.calendar
//        },
//        modifier = modifier
//            .fillMaxWidth()
//            .pointerInput(selectedDate) {
//                awaitEachGesture {
//                    awaitFirstDown(pass = PointerEventPass.Initial)
//                    val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
//                    if (upEvent != null) {
//                        showModal = true
//                    }
//                }
//            }
//    )
//
//    if (showModal) {
//        DatePickerModal(
//            onDateSelected = onDateSelected,
//            onDismiss = { showModal = false }
//        )
//    }
//}