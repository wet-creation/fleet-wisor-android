package ua.com.fleetwisor.features.drivers.domain.models

data class Driver(
    val id: Int = -1,
    val name: String = "",
    val surname: String = "",
    val phoneNumber: String = "",
    val driverLicenseNumber: String = ""
)