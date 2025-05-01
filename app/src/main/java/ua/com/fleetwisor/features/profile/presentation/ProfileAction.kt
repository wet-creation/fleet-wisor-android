package ua.com.fleetwisor.features.profile.presentation

import ua.com.fleetwisor.features.cars.domain.models.FuelType

sealed interface ProfileAction {
    data object OnLogOut : ProfileAction
    data object ChangeInfo : ProfileAction
    data object SaveNewPassword : ProfileAction
    data object TogglePasswordVisibility : ProfileAction
    data object OnErrorCloseClick : ProfileAction
    data object SaveFuelTypeSettings : ProfileAction

    data class InputName(val value: String) : ProfileAction
    data class InputSurname(val value: String) : ProfileAction
    data class InputEmail(val value: String) : ProfileAction
    data class InputOldPassword(val value: String) : ProfileAction
    data class InputNewPassword(val value: String) : ProfileAction
    data class InputConfirmPassword(val value: String) : ProfileAction

    data class SelectUnit(val type: FuelType, val unitId: Int) : ProfileAction
}