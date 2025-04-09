package ua.com.fleetwisor.core.domain.utils

fun String.isDouble() = Regex("^[0-9]*(\\.[0-9]*)?$").matches(this)

fun String.isNotEmptyOrBlank() = this.isNotEmpty() && this.isNotBlank()
