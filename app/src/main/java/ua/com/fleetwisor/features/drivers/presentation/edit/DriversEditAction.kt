package ua.com.fleetwisor.features.drivers.presentation.edit

sealed interface DriversEditAction {
    data object SaveDriver : DriversEditAction
    data object NavigateBack : DriversEditAction

}