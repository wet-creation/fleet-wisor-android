package ua.com.fleetwisor.features.drivers.domain.models

import java.time.LocalDate

data class CreateDriver(
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val driverLicenseNumber: String = "",
    val birthdayDate: LocalDate = LocalDate.now(),
    val salary: Double = 0.0,
)