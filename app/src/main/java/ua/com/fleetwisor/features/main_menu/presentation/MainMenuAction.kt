package ua.com.fleetwisor.features.main_menu.presentation

sealed interface MainMenuAction {
    data object SelectCars : MainMenuAction

    data class SelectPeriod(val isStartDate: Boolean) : MainMenuAction
    data class SelectCarForReport(val id: Int) : MainMenuAction
    data class SearchCars(val searchValue: String) : MainMenuAction
}
