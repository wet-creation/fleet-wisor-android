package ua.com.fleetwisor.features.main_menu.presentation

import ua.com.fleetwisor.core.domain.utils.Index

sealed interface MainMenuAction {
    data object OnModalSheetClose : MainMenuAction
    data object ShowCarSearch : MainMenuAction

    data class SelectPeriod(val start: Long, val end: Long) : MainMenuAction
    data class SelectCarForReport(val index: Index) : MainMenuAction
    data class SearchCars(val searchValue: String) : MainMenuAction
}
