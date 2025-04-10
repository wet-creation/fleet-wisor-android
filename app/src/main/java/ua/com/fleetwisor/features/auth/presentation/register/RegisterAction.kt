package ua.com.fleetwisor.features.auth.presentation.register

import ua.com.fleetwisor.features.auth.presentation.auth.AuthAction

sealed interface RegisterAction {
    data object NavigateLogin : RegisterAction

}
