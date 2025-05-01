package ua.com.fleetwisor.core.data.network.services.profile.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.core.data.network.services.common.dto.UnitsDto
import ua.com.fleetwisor.core.data.network.services.common.dto.UnitsUpdate

@Serializable
data class UserSettingsDto(
    val fuelUnits: List<UnitsDto>,
)
@Serializable
data class UserSettingsUpdate(
    val fuelUnits: List<UnitsUpdate>,
)
