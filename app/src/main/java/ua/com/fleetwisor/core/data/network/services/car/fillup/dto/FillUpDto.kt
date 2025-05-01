package ua.com.fleetwisor.core.data.network.services.car.fillup.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto

@Serializable
data class FillUpDto(
    val id: Int,
    val time: String,
    val price: Double,
    val checkUrl: String,
    val car: CarDto,
    val amount: Double
)
