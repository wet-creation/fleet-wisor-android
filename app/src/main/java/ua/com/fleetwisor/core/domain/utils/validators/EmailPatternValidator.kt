package ua.com.fleetwisor.core.domain.utils.validators

object EmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return value.matches(emailRegex.toRegex())
    }
}