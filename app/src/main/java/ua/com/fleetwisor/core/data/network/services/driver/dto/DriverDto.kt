package ua.com.fleetwisor.core.data.network.services.driver.dto

import kotlinx.serialization.Serializable

@Serializable
data class DriverDto(
    val id: Int,
    val name: String,
    val surname: String,
    val phone: String,
    val driverLicenseNumber: String,
    val frontLicensePhotoUrl: String,
    val backLicensePhotoUrl: String,
    val birthdayDate: String,
    val salary: Double,
)
