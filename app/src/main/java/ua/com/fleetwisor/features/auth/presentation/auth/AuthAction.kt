package ua.com.fleetwisor.features.auth.presentation.auth

sealed interface AuthAction {

    data object NavigateRegister : AuthAction
    data object NavigateLogin : AuthAction

}
