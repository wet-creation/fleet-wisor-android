package ua.com.fleetwisor.core.data.network.services.car.maintenance.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto

@Serializable
data class MaintenanceDto(
    val id: Int,
    val time: String,
    val description: String,
    val checkUrl: String,
    val car: CarDto,
    val price: Double,
)
