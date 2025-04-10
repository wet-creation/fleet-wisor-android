package ua.com.fleetwisor.features.profile.presentation

data class ProfileState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)