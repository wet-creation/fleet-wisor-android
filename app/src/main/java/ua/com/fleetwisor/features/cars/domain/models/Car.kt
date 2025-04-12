package ua.com.fleetwisor.features.cars.domain.models

import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class Car(
    val id: Int = -1,
    val brandName: String = "",
    val color: String? = null,
    val vin: String? = null,
    val model: String? = null,
    val licensePlate: String = "",
    val mileAge: Long = 0,
    val fuelTypes: List<FuelType> = emptyList(),
    val carBody: CarBody = CarBody(),
    val drivers: List<Driver> = emptyList(),
)