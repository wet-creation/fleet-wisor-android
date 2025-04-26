package ua.com.fleetwisor.features.cars.domain.models

import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class Car(
    val id: Int = -1,
    val brandName: String = "",
    val color: String = "",
    val vin: String = "",
    val model: String = "",
    val licensePlate: String = "",
    val mileAge: Long = 0,
    val fuelTypes: List<FuelType> = emptyList(),
    val carBody: CarBody = CarBody(),
    val drivers: List<Driver> = emptyList(),
) {
    val name
        get() = "$color $brandName $model"
}