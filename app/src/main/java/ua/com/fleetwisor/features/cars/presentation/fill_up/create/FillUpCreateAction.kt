package ua.com.fleetwisor.features.cars.presentation.fill_up.create

sealed interface FillUpCreateAction {
    companion object : FillUpCreateAction
    data object NavigateBack : FillUpCreateAction

    data class ChangeTabIndex(val index: Int) : FillUpCreateAction
}