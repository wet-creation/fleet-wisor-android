package ua.com.fleetwisor.features.cars.presentation.cars.create

import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.Insurance

data class CarCreateState(
    val selectedTab: Int = 0,
    val car: Car = Car(),
    val insurance: Insurance = Insurance()
)