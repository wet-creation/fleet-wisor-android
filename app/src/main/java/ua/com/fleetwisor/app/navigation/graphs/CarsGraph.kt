package ua.com.fleetwisor.app.navigation.graphs

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
    data class CarEdit(
        val carId: Int
    )
    @Serializable
    data object FillUp
    @Serializable
    data object FillUpCreate
    @Serializable
    data class FillUpEdit (
        val fillUpId: Int
    )
    @Serializable
    data object Maintenance
    @Serializable
    data object MaintenanceCreate
    @Serializable
    data object MaintenanceEdit

}