package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction

sealed interface FillUpEditAction {
    companion object : FillUpEditAction
    data object NavigateBack : FillUpEditAction

    data class ChangeTabIndex(val index: Int) : FillUpEditAction
}