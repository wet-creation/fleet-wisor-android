package ua.com.fleetwisor.features.auth.presentation.register

import ua.com.fleetwisor.features.auth.presentation.auth.AuthAction

sealed interface RegisterAction {
    data object NavigateLogin : RegisterAction
    data object OnPasswordVisibilityClick :
        RegisterAction

    object OnRegisterClick :
        RegisterAction

    data object OnErrorRegistration : RegisterAction
    data class OnNameChange(val name: String) : RegisterAction

    data class OnSurnameChange(val surname: String) : RegisterAction

    data class OnEmailChange(val email: String) :
        RegisterAction

    data class OnPasswordChange(val password: String) :
        RegisterAction

    data class OnPasswordCheckChange(val passwordCheck: String) :
        RegisterAction
}
