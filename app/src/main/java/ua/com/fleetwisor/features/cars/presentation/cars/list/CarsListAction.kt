package ua.com.fleetwisor.features.cars.presentation.cars.list

sealed interface CarsListAction {
    data object NavigateCreate : CarsListAction
    data object NavigateBack : CarsListAction

    data class NavigateEdit(val id: Int) : CarsListAction
}