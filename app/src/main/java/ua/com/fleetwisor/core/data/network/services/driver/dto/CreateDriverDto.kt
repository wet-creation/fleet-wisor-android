package ua.com.fleetwisor.core.data.network.services.driver.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateDriverDto(
    val name: String,
    val surname: String,
    val phone: String,
    val driverLicenseNumber: String,
    val birthdayDate: String,
    val salary: Double,
)