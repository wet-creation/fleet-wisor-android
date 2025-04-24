package ua.com.fleetwisor.features.drivers.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.drivers.domain.models.CreateDriver
import ua.com.fleetwisor.features.drivers.domain.models.Driver

interface DriverRepository {

    suspend fun getList(): Results<List<Driver>, DataError.Network>
    suspend fun createDriver(
        driver: CreateDriver,
        front: Pair<String, ByteArray>?,
        back: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

}