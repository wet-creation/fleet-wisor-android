package ua.com.fleetwisor.features.drivers.presentation.main

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class DriversListState(
    val drivers: List<Driver> = emptyList(),
    val driversFilter: List<Driver> = emptyList(),
    val searchValue: String = "",
    val isLoading: Boolean = false,
    val error: UiText = emptyUiText

)