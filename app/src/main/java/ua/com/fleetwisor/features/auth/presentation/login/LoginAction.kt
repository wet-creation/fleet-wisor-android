package ua.com.fleetwisor.features.auth.presentation.login

sealed interface LoginAction {
    data object OnLogin : LoginAction
    data object TogglePasswordVisibility : LoginAction
  data  object OnErrorCloseClick : LoginAction

    data class InputPassword(val password: String) : LoginAction
    data class InputEmail(val email: String) : LoginAction
}
