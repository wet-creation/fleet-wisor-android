package ua.com.fleetwisor.core.data.network.services.profile.dto

import kotlinx.serialization.Serializable

@Serializable
data class ChangePassword(
    val oldPassword: String,
    val newPassword: String
)
