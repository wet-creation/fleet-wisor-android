package ua.com.fleetwisor.features.drivers.domain.models

import ua.com.fleetwisor.core.domain.utils.ImageUrl
import java.time.LocalDate

data class Driver(
    val id: Int = -1,
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val driverLicenseNumber: String = "",
    val frontLicensePhotoUrl: ImageUrl = "",
    val backLicensePhotoUrl: ImageUrl = "",
    val birthdayDate: LocalDate = LocalDate.now(),
    val salary: Double = 0.0,
) {
    val fullName: String
        get() = "$name $surname"
}