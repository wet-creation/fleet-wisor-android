package ua.com.fleetwisor.features.profile.presentation

sealed interface ProfileAction {
    data object OnLogOut: ProfileAction
}