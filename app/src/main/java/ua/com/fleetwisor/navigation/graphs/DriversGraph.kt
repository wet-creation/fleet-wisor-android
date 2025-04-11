package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
data object DriversGraph {
    @Serializable
    data object Driver
    @Serializable
    data object Create
}