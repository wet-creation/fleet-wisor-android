package ua.com.fleetwisor.features.cars.domain.models

import java.time.LocalDate

data class Insurance(
    val startDate: LocalDate = LocalDate.now(),
    val endDate: LocalDate = LocalDate.now(),
    val carId: Int = -1,
    val photoUrl: String = "",
)
