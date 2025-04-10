package ua.com.fleetwisor.features.drivers.presentation.create

data class DriverCreateState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)