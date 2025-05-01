package ua.com.fleetwisor.features.cars.presentation.fill_up.list

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.FillUp

data class FilUpListState(
    val fillUps: List<FillUp> = emptyList(),
    val filteredFillUps: List<FillUp> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText = emptyUiText,
    val searchValue: String = ""
)