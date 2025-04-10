package ua.com.fleetwisor.features.drivers.presentation.main

import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class DriversListState(
    val paramOne: String = "default",
    val drivers: List<Driver> = listOf(
        Driver(
            name = "Вадим",
            surname = "Мармеладов",
            phoneNumber = "+380672898920",
            driverLicenseNumber = "0147819"
        )
    )
)