package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceEditState(
    val selectedTab: Int = 0,
    val maintenance: Maintenance = Maintenance()
)