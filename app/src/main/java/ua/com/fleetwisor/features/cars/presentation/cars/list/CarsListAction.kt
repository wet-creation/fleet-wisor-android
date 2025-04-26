package ua.com.fleetwisor.features.cars.presentation.cars.list

sealed interface CarsListAction {
    data object NavigateCreate : CarsListAction
    data object NavigateBack : CarsListAction
    data object Refresh : CarsListAction

    data class NavigateEdit(val id: Int) : CarsListAction

    data class InputSearch(val value: String) : CarsListAction
}