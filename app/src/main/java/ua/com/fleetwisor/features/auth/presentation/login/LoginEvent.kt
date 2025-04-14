package ua.com.fleetwisor.features.auth.presentation.login

import ua.com.fleetwisor.core.presentation.ui.utils.UiText

sealed interface LoginEvent {
    data class Error(val error: UiText): LoginEvent
    data object LoginSuccess: LoginEvent
}