package ua.com.fleetwisor.features.cars.presentation.cars.edit

sealed interface CarEditAction {
    data object NavigateBack : CarEditAction

    data class ChangeTabIndex(val index: Int) : CarEditAction
    companion object : CarEditAction
}