package ua.com.fleetwisor.core.data.network.services.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterDto(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)
