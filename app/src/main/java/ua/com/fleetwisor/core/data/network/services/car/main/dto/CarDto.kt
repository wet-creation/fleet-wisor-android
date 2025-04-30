package ua.com.fleetwisor.core.data.network.services.car.main.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.core.data.network.services.common.dto.CarBodyDto
import ua.com.fleetwisor.core.data.network.services.common.dto.SimpleFuelTypeDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto

@Serializable
data class CarDto(
    val id: Int = -1,
    val brandName: String = "",
    val color: String? = null,
    val vin: String? = null,
    val model: String? = null,
    val licensePlate: String? = null,
    val mileAge: Long = 0,
    val drivers: List<DriverDto> = listOf(),
    val fuelTypes: List<SimpleFuelTypeDto> = listOf(),
    val carBody: CarBodyDto
)

