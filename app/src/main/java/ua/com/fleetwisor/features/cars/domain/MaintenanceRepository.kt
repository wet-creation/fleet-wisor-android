package ua.com.fleetwisor.features.cars.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

interface MaintenanceRepository {

    suspend fun getAll(): Results<List<Maintenance>, DataError.Network>
}