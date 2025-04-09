package ua.com.fleetwisor.core.domain.utils.validators

class UserDataValidator(
    private val patternValidator: PatternValidator
) {
    fun isValidPhoneNumber(number: String): Boolean {
        return number.all { it.isDigit() } && (number.length == 9)
    }

    fun isEdrNumberValid(number: String): Boolean {
        return number.all { it.isDigit() } && (number.length == 8 || number.length == 10)
    }

    fun isValidEmail(email: String): Boolean {
        return patternValidator.matches(email.trim())
    }

    fun validatePassword(password: String): PasswordValidationState {
        val hasMinLength = password.length >= MIN_PASSWORD_LENGTH
        val hasDigit = password.any { it.isDigit() }
        val hasLowerCaseCharacter = password.any { it.isLowerCase() }
        val hasUpperCaseCharacter = password.any { it.isUpperCase() }

        return PasswordValidationState(
            hasMinLength = hasMinLength,
            hasNumber = hasDigit,
            hasLowerCaseCharacter = hasLowerCaseCharacter,
            hasUpperCaseCharacter = hasUpperCaseCharacter
        )
    }

    companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}