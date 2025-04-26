package ua.com.fleetwisor.core.data.network.services.car.dto

import kotlinx.serialization.Serializable

@Serializable
data class CarBodyDto(
    val id: Int = -1,
    val name: String = ""
)
