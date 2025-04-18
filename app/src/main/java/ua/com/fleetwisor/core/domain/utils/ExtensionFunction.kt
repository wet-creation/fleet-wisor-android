package ua.com.fleetwisor.core.domain.utils

import java.text.DateFormatSymbols
import java.time.LocalDate
import java.util.Locale

fun String.isDouble() = Regex("^[0-9]*(\\.[0-9]*)?$").matches(this)

fun String.isNotEmptyOrBlank() = this.isNotEmpty() && this.isNotBlank()

fun LocalDate.firstDayOfMonth(): LocalDate {
    val month = LocalDate.now().month
    val year = LocalDate.now().year
    return LocalDate.of(year, month, 1)
}

fun LocalDate.monthToString(locale: Locale = Locale.getDefault()): String {
    return DateFormatSymbols(locale).months[this.month.ordinal]
}
