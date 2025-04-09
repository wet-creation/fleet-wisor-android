package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
sealed interface DriversGraph {
    @Serializable
    data object Driver : DriversGraph
}