package ua.com.fleetwisor.features.cars.domain.models

import java.time.LocalDate

data class Insurance(
    val id: Int = -1,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val carId: Int = -1,
    val photoUrl: String = "",
)
