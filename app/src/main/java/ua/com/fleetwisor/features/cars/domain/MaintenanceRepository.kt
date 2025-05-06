package ua.com.fleetwisor.features.cars.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

interface MaintenanceRepository {

    suspend fun getAll(): Results<List<Maintenance>, DataError.Network>
    suspend fun get(id: Int): Results<Maintenance, DataError.Network>
    suspend fun save(
        maintenance: Maintenance,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun delete(
        maintenanceId: Int
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun edit(
        maintenance: Maintenance,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>
}