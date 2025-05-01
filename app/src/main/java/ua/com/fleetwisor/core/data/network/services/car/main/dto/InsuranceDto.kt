package ua.com.fleetwisor.core.data.network.services.car.main.dto

import kotlinx.serialization.Serializable

@Serializable
data class InsuranceDto(
    val id: Int = -1,
    val startDate: String = "",
    val endDate: String = "",
    val carId: Int = -1,
    val photoUrl: String = "",
)
