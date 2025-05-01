package ua.com.fleetwisor.core.data.network.services.profile.dto

import kotlinx.serialization.Serializable

@Serializable
data class OwnerDto(
    val id: Int = 0,
    val email: String = "",
    val name: String = "",
    val surname: String = "",
)
