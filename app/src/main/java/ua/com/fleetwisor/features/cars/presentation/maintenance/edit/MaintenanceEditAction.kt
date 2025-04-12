package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import ua.com.fleetwisor.features.cars.presentation.maintenance.create.MaintenanceCreateAction

sealed interface MaintenanceEditAction {
    companion object: MaintenanceEditAction
    data  object NavigateBack : MaintenanceEditAction
    data  class ChangeTabIndex(val index: Int) : MaintenanceEditAction
}