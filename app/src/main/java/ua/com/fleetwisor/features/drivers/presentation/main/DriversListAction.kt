package ua.com.fleetwisor.features.drivers.presentation.main

sealed interface DriversListAction {
    data class NavigateEdit(val id: Int) : DriversListAction
    data class SearchDriver(val value: String) : DriversListAction
    data object NavigateCreate : DriversListAction
    data object Refresh : DriversListAction
}