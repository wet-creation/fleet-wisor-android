package ua.com.fleetwisor.features.cars.domain.models

import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

data class FuelType(
    val id: Int = -1,
    val name: String = "",
    val units: List<FuelUnits> = emptyList()
)
