package ua.com.fleetwisor.features.cars.domain.models

import java.time.LocalDateTime

data class Maintenance(
    val id: Int = -1,
    val time: LocalDateTime = LocalDateTime.now(),
    val description: String = "",
    val checkUrl: String? = null,
    val car: Car = Car(),
    val price: Double = 0.0,
)
