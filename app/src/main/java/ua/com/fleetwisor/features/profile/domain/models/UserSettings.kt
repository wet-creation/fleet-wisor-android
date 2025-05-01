package ua.com.fleetwisor.features.profile.domain.models

data class UserSettings(
    val fuelUnits: List<FuelUnits> = emptyList()
)

