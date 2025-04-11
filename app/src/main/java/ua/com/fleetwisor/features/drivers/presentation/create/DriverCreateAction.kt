package ua.com.fleetwisor.features.drivers.presentation.create

sealed interface DriverCreateAction {
    data object NavigateBack : DriverCreateAction
    data object SaveDriver : DriverCreateAction
}