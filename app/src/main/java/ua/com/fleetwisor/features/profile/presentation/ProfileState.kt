package ua.com.fleetwisor.features.profile.presentation

import ua.com.fleetwisor.core.domain.utils.validators.PasswordValidationState
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.profile.domain.models.Owner

data class ProfileState(
    val owner: Owner = Owner(),
    val newOwner: Owner = Owner(),
    val oldPassword: String = "",
    val newPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val savingInProgress: Boolean = false,
    val saved: Boolean = false,
    val confirmPassword: String = "",
    val error: UiText = emptyUiText,
    val passwordError: UiText = emptyUiText,
    val isPasswordValid: PasswordValidationState = PasswordValidationState(),

)