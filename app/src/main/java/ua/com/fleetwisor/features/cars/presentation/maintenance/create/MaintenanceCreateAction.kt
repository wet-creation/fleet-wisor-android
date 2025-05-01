package ua.com.fleetwisor.features.cars.presentation.maintenance.create


sealed interface MaintenanceCreateAction {
    companion object: MaintenanceCreateAction
    data object NavigateBack : MaintenanceCreateAction
    data class ChangeTabIndex(val index: Int) : MaintenanceCreateAction

}