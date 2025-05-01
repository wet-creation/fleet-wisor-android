package ua.com.fleetwisor.app

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText


data class MainState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuth: Boolean = false,
    val error: UiText = emptyUiText
)
