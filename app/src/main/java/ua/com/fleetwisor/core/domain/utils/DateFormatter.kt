package ua.com.fleetwisor.core.domain.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

fun LocalDate.formatDate(): String {
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return this.format(formatter)
}

fun String.formatDate(): LocalDate {
    if (!this.isNotEmptyOrBlank())
        return LocalDate.now()
    val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
    return LocalDate.parse(this, formatter)
}

fun LocalDateTime.formatTime(): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm", Locale.getDefault())
    return this.format(formatter)
}

fun String.parseDateOrNull(): LocalDate? {
    if (this.isNotEmptyOrBlank())
        return LocalDate.parse(this)
    return null
}

fun String.parseTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.getDefault())
    return LocalDateTime.parse(this, formatter)
//    val gmtZonedDateTime = gmtDateTime.atZone(ZoneId.of("GMT+2"))
//
//    // Преобразуем в системное время
//    val systemZonedDateTime = gmtZonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
}