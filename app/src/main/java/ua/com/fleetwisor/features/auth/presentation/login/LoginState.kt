package ua.com.fleetwisor.features.auth.presentation.login

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState

data class LoginState(
    val placeholder: Int = 0,
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isEmailValid: Boolean = false,
    val canLogin: Boolean = false,
    val isLoggingIn: Boolean = false,
    val error: UiText = emptyUiText,
    val loginState: RegisterState = RegisterState.NONE
)