package ua.com.fleetwisor.features.cars.presentation.main

sealed interface CarMainAction {
    data object NavigateFillUp : CarMainAction
    data object NavigateMaintenance : CarMainAction
    data object NavigateCars : CarMainAction
}