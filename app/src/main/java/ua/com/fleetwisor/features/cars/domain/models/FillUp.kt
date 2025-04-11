package ua.com.fleetwisor.features.cars.domain.models

data class FillUp(
    val id: Int = 0,
    val time: String = "",
    val price: Double = 0.0,
    val checkUrl: String = "",
    val car: Car = Car()
)
