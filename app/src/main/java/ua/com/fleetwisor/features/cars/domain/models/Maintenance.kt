package ua.com.fleetwisor.features.cars.domain.models

data class Maintenance(
    val id: Int = -1,
    val time: String = "",
    val description: String = "",
    val checkUrl: String? = null,
    val car: Car = Car(),
    val price: Double = 0.0,
)
