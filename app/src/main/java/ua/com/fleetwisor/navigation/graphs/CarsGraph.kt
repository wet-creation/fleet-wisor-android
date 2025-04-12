package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
data object CarsGraph {
    @Serializable
    data object CarMain
    @Serializable
    data object CarList
    @Serializable
    data object CarCreate
    @Serializable
    data object FillUp
    @Serializable
    data object Maintenance
}