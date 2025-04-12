package ua.com.fleetwisor.features.cars.domain.models

data class Insurance(
    val startDate: String = "",
    val endDate: String = "",
    val carId: Int = -1,
    val photoUrl: String = "",
)
