package ua.com.fleetwisor.features.cars.presentation.maintenance.list


sealed interface MaintenanceListAction {
    data object NavigateCreate : MaintenanceListAction
    data object Refresh : MaintenanceListAction
    data object NavigateBack : MaintenanceListAction

    data class NavigateEdit(val id: Int) : MaintenanceListAction
    data class InputSearch(val value: String) : MaintenanceListAction

}