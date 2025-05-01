package ua.com.fleetwisor.core.data.network.services.common.dto

import kotlinx.serialization.Serializable
import ua.com.fleetwisor.features.cars.domain.models.CarBody

@Serializable
data class CarBodyDto(
    val id: Int = -1,
    val name: String = ""
) {

    fun asCarBody() = CarBody(
        id = id,
        name = name
    )

}