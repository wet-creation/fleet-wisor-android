package ua.com.fleetwisor.features.auth.domain.models

data class AuthInfo(
    val refreshToken: String = "",
    val accessToken: String = ""
)

data class RegisterInfo(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)

