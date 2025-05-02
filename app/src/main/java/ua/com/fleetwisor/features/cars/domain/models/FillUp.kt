package ua.com.fleetwisor.features.cars.domain.models

import ua.com.fleetwisor.features.profile.domain.models.FuelUnits
import java.time.LocalDateTime


data class FillUp(
    val id: Int = 0,
    val time: LocalDateTime = LocalDateTime.now(),
    val price: Double = 0.0,
    val checkUrl: String? = null,
    val amount: Double = 0.0,
    val fuelType: FuelType = FuelType(),
    val fuelUnits: FuelUnits = FuelUnits(),
    val car: Car = Car()
)
