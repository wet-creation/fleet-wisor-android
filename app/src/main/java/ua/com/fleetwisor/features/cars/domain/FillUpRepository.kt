package ua.com.fleetwisor.features.cars.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.cars.domain.models.FillUp

interface FillUpRepository {

    suspend fun getAll(): Results<List<FillUp>, DataError.Network>
    suspend fun get(id: Int): Results<FillUp, DataError.Network>
    suspend fun save(
        fillUp: FillUp,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun delete(
        fillUpId: Int
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun edit(
        fillUp: FillUp,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

}