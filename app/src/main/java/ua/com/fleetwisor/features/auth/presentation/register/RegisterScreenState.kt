package ua.com.fleetwisor.features.auth.presentation.register

import ua.com.fleetwisor.core.domain.utils.validators.PasswordValidationState
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState

data class RegisterScreenState(
    val name: String = "",
    val surname: String = "",
    val email: String = "",
    val isEmailValid: Boolean = false,
    val password: String = "",
    val isPasswordValid: PasswordValidationState = PasswordValidationState(),
    val passwordConformation: String = "",
    val isRegistering: Boolean = false,
    val canRegister: Boolean = false,
    val isPasswordVisible: Boolean = false,
    val registerState: RegisterState = RegisterState.NONE,
    val error: UiText = emptyUiText
)
