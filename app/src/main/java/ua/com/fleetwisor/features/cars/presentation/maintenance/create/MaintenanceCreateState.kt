package ua.com.fleetwisor.features.cars.presentation.maintenance.create

import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceCreateState(
    val selectedTab: Int = 0,
    val maintenance: Maintenance = Maintenance()
)