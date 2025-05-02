package ua.com.fleetwisor.core.data.network.services.car.fillup.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.data.network.services.common.dto.FuelTypeDto
import ua.com.fleetwisor.core.data.network.services.common.dto.UnitsDto

@Serializable
data class FillUpDto(
    val id: Int,
    val time: String,
    val price: Double,
    val checkUrl: String,
    val car: CarDto,
    val fuelType: FuelTypeDto,
    val unit: UnitsDto,
    val amount: Double
)
@Serializable
data class FillUpCreate(
    val time: String,
    val price: Double,
    val carId: Int,
    val unitId: Int,
    val amount: Double
)
