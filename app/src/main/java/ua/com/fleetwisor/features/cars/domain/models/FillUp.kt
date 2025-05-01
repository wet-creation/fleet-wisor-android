package ua.com.fleetwisor.features.cars.domain.models

import java.time.LocalDateTime


data class FillUp(
    val id: Int = 0,
    val time: LocalDateTime = LocalDateTime.now(),
    val price: Double = 0.0,
    val checkUrl: String? = null,
    val amount: Double = 0.0,
    val car: Car = Car()
)
