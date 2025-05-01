package ua.com.fleetwisor.core.presentation.theme.components.select_controls

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.buttons.standart.ThirdAgroswitButton
import ua.com.fleetwisor.core.presentation.theme.components.dropdown.SelectedDropDown
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import java.text.DateFormatSymbols
import java.time.LocalDate
import java.time.YearMonth
import java.util.Locale

@Composable
fun DateSelector(
    modifier: Modifier = Modifier,
    title: String,
    initValue: LocalDate = LocalDate.now(),
    onSave: (LocalDate) -> Unit
) {
    val currentDate = LocalDate.now()
    val locale = Locale.getDefault()
    val years = remember { (1950..currentDate.year).toList() }
    val initYear = (years.size - 1) - (currentDate.year - initValue.year)
    var selectedDay by remember { mutableIntStateOf(initValue.dayOfMonth) }
    var selectedMonth by remember { mutableIntStateOf(initValue.monthValue) }
    var selectedYear by remember { mutableIntStateOf(initYear) }

    val days = remember(selectedMonth, selectedYear) {
        if (selectedMonth == currentDate.monthValue)
            currentDate.dayOfMonth
        else
            getDaysInMonth(years[selectedYear], selectedMonth)
    }
    val months = remember(selectedYear) {
        if (selectedYear == years.size - 1)
            DateFormatSymbols(locale).months.take(currentDate.monthValue)
        else
            DateFormatSymbols(locale).months.toList()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            color = FleetWisorTheme.colors.brandPrimaryNormal,
            style = FleetWisorTheme.typography.headlineSmall
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 6.dp)
        ) {
            SelectedDropDown(
                Modifier.weight(1f),
                containerHeight = 28.dp,
                selectedItem = selectedDay - 1,
                displayedItemsCount = 6,
                overlapping = false,
                items = {
                    (1..days).map {
                        DropDownItemState(it, it.toString().padStart(2, '0'))
                    }
                },
                onItemChange = {
                    selectedDay = it + 1
                }
            )
            SelectedDropDown(
                Modifier.weight(1f),
                containerHeight = 28.dp,
                selectedItem = selectedMonth - 1,
                displayedItemsCount = 6,
                overlapping = false,
                items = {
                    (months).mapIndexed { index, month ->
                        DropDownItemState(index, month)
                    }
                },
                onItemChange = {
                    selectedMonth = it + 1
                    selectedDay = 1
                }

            )
            SelectedDropDown(
                Modifier.weight(1f),
                containerHeight = 28.dp,

                selectedItem = selectedYear,
                displayedItemsCount = 6,
                overlapping = false,
                items = {
                    years.map {
                        DropDownItemState(it, it.toString())
                    }
                },
                onItemChange = {
                    selectedYear = it
                    selectedMonth = 1
                    selectedDay = 1
                }
            )
            Icon(
                modifier = Modifier.size(20.dp),
                tint = FleetWisorTheme.colors.brandPrimaryNormal,
                painter = FleetWisorTheme.icons.calendar,
                contentDescription = ""
            )

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PrimaryButton(
                modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.save_text),
            ) {
                val date = LocalDate.of(years[selectedYear], selectedMonth, selectedDay)
                onSave(date)
            }
            ThirdAgroswitButton(
                modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                text = stringResource(id = R.string.dismiss_text),
                color = FleetWisorTheme.colors.brandPrimaryNormal
            ) {
                selectedDay = currentDate.dayOfMonth
                selectedMonth = currentDate.monthValue
                selectedYear = years.size - 1
            }

        }
    }

}

private fun getDaysInMonth(year: Int, month: Int): Int {
    return YearMonth.of(year, month).lengthOfMonth()
}


@Preview
@Composable
private fun DateSelectorPrev() {
    Column(Modifier.padding(vertical = 12.dp, horizontal = 20.dp)) {
        val context = LocalContext.current
        DateSelector(title = "Введіть дату", initValue = LocalDate.now()) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
        }

    }

}