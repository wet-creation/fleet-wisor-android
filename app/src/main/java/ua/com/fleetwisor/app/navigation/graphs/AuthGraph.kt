package ua.com.fleetwisor.app.navigation.graphs

import kotlinx.serialization.Serializable
@Serializable
data object AuthGraph {
    @Serializable
    data object Auth
    @Serializable
    data object Login
    @Serializable
    data object Register
}