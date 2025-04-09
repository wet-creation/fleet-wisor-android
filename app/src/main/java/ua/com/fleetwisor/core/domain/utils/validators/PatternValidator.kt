package ua.com.fleetwisor.core.domain.utils.validators

interface PatternValidator {
    fun matches(value: String): Boolean
}