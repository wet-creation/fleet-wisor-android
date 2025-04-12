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
    data object CarEdit
    @Serializable
    data object FillUp
    @Serializable
    data object FillUpCreate
    @Serializable
    data object FillUpEdit
    @Serializable
    data object Maintenance
}