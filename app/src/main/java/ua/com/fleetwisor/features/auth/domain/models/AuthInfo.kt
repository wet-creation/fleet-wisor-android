package ua.com.fleetwisor.features.auth.domain.models

data class AuthInfo(
    val accessToken: String = "",
    val refreshToken: String = ""
)

data class RegisterInfo(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)

