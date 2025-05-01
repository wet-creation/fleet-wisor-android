package ua.com.fleetwisor.core.domain.utils

import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.text.DateFormatSymbols
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Locale


fun String.isDouble() = Regex("^[0-9]*(\\.[0-9]*)?$").matches(this)

fun Long.millisToLocalDate(): LocalDate {
    return Instant.ofEpochMilli(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

fun String.isNotEmptyOrBlank() = this.isNotEmpty() && this.isNotBlank()

fun List<String>.isNotEmptyOrBlank() = this.all { it.isNotEmptyOrBlank() }

fun LocalDate.firstDayOfMonth(): LocalDate {
    val month = LocalDate.now().month
    val year = LocalDate.now().year
    return LocalDate.of(year, month, 1)
}

fun LocalDate?.monthToString(locale: Locale = Locale.getDefault()): String {
    if (this == null)
        return ""
    return DateFormatSymbols(locale).months[this.month.ordinal]
}

fun InputStream.toByteArray(): ByteArray? {
    val byteBuffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val buffer = ByteArray(bufferSize)

    var len = 0
    while ((this.read(buffer).also { len = it }) != -1) {
        byteBuffer.write(buffer, 0, len)
    }
    return byteBuffer.toByteArray()
}

fun LocalDate.toMillis(): Long {
    Log.d(this)
    val systemZone = ZoneId.systemDefault()
    return this.atStartOfDay(systemZone).toInstant()
        .toEpochMilli() + 20_000_000
}
