package ua.com.fleetwisor.core.data.network.services.common.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

@Serializable
data class UnitsDto(
    val id: Int,
    val name: String,
) {
    fun asUnits(): FuelUnits {
        return FuelUnits(
            id = id,
            name = name,
        )
    }
}
@Serializable
data class UnitsUpdate(
    val idFuelType: Int,
    val idUnit: Int,
)
