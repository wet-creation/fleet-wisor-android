package ua.com.fleetwisor.features.cars.presentation.cars.edit

import android.net.Uri
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class CarEditState(
    val selectedTab: Int = 0,
    val driverSearchValue: String = "",
    val car: Car = Car(),
    val carOrigin: Car = Car(),
    val drivers: List<Driver> = emptyList(),
    val driversFilter: List<Driver> = emptyList(),
    val carBodies: List<CarBody> = emptyList(),
    val fuelTypes: List<FuelType> = emptyList(),
    val selectedDrivers: List<Int> = emptyList(),
    val error: UiText = emptyUiText,
    val insurance: Insurance = Insurance(),
    val originInsurance: Insurance = Insurance(),
    val selectedPhoto: Uri? = null
) {
    val canBeSaved
        get() = (car.canBeSaved && carOrigin != car) ||  insurance != originInsurance || selectedPhoto != null
}