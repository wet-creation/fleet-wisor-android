package ua.com.fleetwisor.features.drivers.presentation.main

sealed interface DriversListAction {
    data class NavigateEdit(val id: Int) : DriversListAction
    data object NavigateCreate : DriversListAction
}