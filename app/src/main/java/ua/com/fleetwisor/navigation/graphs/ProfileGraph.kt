package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable
@Serializable
sealed interface ProfileGraph {
    @Serializable
    data object Profile : ProfileGraph
}