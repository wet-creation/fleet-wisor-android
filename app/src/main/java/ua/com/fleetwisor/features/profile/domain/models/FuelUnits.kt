package ua.com.fleetwisor.features.profile.domain.models

import ua.com.fleetwisor.features.cars.domain.models.FuelType

data class FuelUnits(
    val id: Int = -1,
    val name: String = "",
    val fuelType: FuelType = FuelType()
)
