package ua.com.fleetwisor.core.data.network.services.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    val email: String,
    val password: String
)
