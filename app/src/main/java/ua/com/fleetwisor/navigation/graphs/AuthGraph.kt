package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable
@Serializable
sealed interface AuthGraph {
    @Serializable
    data object Auth: AuthGraph
    @Serializable
    data object Login: AuthGraph
    @Serializable
    data object Register: AuthGraph
}