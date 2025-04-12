package ua.com.fleetwisor.features.cars.presentation.cars.create

sealed interface CarCreateAction {
    data object NavigateBack : CarCreateAction

    data class ChangeTabIndex(val index: Int) : CarCreateAction
    companion object : CarCreateAction
}