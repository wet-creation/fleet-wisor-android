package ua.com.fleetwisor.features.cars.presentation.fill_up.list

sealed interface FilUpListAction {
    data class NavigateEdit(val id: Int): FilUpListAction
    data object NavigateBack: FilUpListAction
    data object NavigateCreate: FilUpListAction
}