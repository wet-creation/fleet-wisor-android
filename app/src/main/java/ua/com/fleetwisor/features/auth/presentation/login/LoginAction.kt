package ua.com.fleetwisor.features.auth.presentation.login

sealed interface LoginAction {
    data object NavigateMainMenu: LoginAction
}
