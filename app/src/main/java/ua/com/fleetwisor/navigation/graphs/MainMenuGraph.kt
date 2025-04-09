package ua.com.fleetwisor.navigation.graphs

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainMenuGraph {
    @Serializable
    object MainMenu: MainMenuGraph
}