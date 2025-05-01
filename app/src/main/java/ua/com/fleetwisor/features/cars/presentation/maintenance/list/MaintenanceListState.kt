package ua.com.fleetwisor.features.cars.presentation.maintenance.list

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceListState(
    val maintenances: List<Maintenance> = emptyList(),
    val filteredMaintenances: List<Maintenance> = emptyList(),
    val searchValue: String = "",
    val isLoading: Boolean = false,
    val error: UiText = emptyUiText
)