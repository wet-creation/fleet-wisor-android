package ua.com.fleetwisor.core.data.network.services.common.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.features.cars.domain.models.FuelType

@Serializable
data class SimpleFuelTypeDto(
    val id: Int = -1,
    val name: String = ""
) {
    fun asFuelType() = FuelType(
        id = id,
        name = name
    )
}
@Serializable
data class FuelTypeDto(
    val id: Int = -1,
    val name: String = "",
    val units: List<UnitsDto> = emptyList()
) {
    fun asFuelType() = FuelType(
        id = id,
        name = name,
        units = units.map { it.asUnits() }
    )
}