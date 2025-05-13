package ua.com.fleetwisor.features.cars.presentation.fill_up.list


sealed interface FilUpListAction {
    data object NavigateBack: FilUpListAction
    data object Refresh : FilUpListAction
    data object NavigateCreate: FilUpListAction
    data object DismissErrorDialog: FilUpListAction

    data class NavigateEdit(val id: Int): FilUpListAction
    data class InputSearch(val value: String) : FilUpListAction


}
