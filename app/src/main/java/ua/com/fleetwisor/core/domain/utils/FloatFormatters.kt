package ua.com.fleetwisor.core.domain.utils

import java.math.BigDecimal

fun Double.toPriceString(): String {
    val formattedNumber = "%.2f".format(this)
    val parts = formattedNumber.split(".", ",")
    val integerPart = parts[0]
    val decimalPart = if (parts.size > 1) parts[1] else ""

    val formattedIntegerPart = integerPart.reversed().chunked(3).joinToString(" ").reversed()

    return if (decimalPart.isNotEmpty() && decimalPart.toIntOrNull() != 0) {
        "$formattedIntegerPart.$decimalPart"
    } else {
        formattedIntegerPart
    }
}

fun BigDecimal.toPriceString(): String {
    val formattedNumber = "%.2f".format(this)
    val parts = formattedNumber.split(".", ",")
    val integerPart = parts[0]
    val decimalPart = if (parts.size > 1) parts[1] else ""

    val formattedIntegerPart = integerPart.reversed().chunked(3).joinToString(" ").reversed()

    return if (decimalPart.isNotEmpty()) {
        "$formattedIntegerPart.$decimalPart"
    } else {
        formattedIntegerPart
    }
}

fun Double.toVolumeString(): String {
    return if (this % 1 != 0.0)
        this.toString()
    else this.toInt().toString()
}

fun Float.toVolumeString(): String {
    return if (this % 1 != 0f)
        this.toString()
    else this.toInt().toString()
}