package ua.com.fleetwisor.features.cars.domain.models

import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class Car(
    val id: Int = -1,
    val brandName: String = "",
    val color: String = "",
    val vin: String = "",
    val model: String = "",
    val licensePlate: String = "",
    val mileAge: Long = 0,
    val fuelTypes: Set<FuelType> = setOf(FuelType()),
    val carBody: CarBody = CarBody(),
    val drivers: List<Driver> = emptyList(),
) {
    val name
        get() = "${if (color.isNotEmptyOrBlank()) "$color $brandName" else brandName} $model"

    val canBeSaved
        get() =
            brandName.isNotEmptyOrBlank() && licensePlate.isNotEmptyOrBlank() && carBody != CarBody() && fuelTypesIsUnique

    private val fuelTypesIsUnique
        get() = fuelTypes.isNotEmpty() && fuelTypes.any { it != FuelType() }


}