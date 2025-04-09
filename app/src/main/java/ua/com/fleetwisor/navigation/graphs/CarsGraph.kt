package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
sealed interface CarsGraph {
    @Serializable
    data object CarMain : CarsGraph
}