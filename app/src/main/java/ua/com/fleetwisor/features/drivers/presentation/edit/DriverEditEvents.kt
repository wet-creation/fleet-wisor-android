package ua.com.fleetwisor.features.drivers.presentation.edit

sealed interface DriverEditEvents {
    data object DeletionComplete: DriverEditEvents
}