package ua.com.fleetwisor.core.data.network.services.auth.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoDto(
    val jwtAccessToken: String,
    val jwtRefreshToken: String
)
@Serializable
data class TokenRequest(
    val jwtRefreshToken: String
)
