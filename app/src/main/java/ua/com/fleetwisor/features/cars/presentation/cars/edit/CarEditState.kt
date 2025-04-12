package ua.com.fleetwisor.features.cars.presentation.cars.edit

import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.Insurance

data class CarEditState(
    val selectedTab: Int = 0,
    val car: Car = Car(),
    val carEdit: Car = Car(),
    val insurance: Insurance = Insurance(),
    val insuranceEdit: Insurance = Insurance()
)