package ua.com.fleetwisor.features.cars.presentation.maintenance.list

sealed interface MaintenanceListAction {
    data object NavigateCreate : MaintenanceListAction
    data object NavigateBack : MaintenanceListAction

    data class NavigateEdit(val id: Int) : MaintenanceListAction
}