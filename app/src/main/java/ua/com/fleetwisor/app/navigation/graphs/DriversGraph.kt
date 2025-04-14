package ua.com.fleetwisor.app.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
data object DriversGraph {
    @Serializable
    data object Driver
    @Serializable
    data object Create
}