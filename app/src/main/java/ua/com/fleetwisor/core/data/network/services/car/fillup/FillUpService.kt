package ua.com.fleetwisor.core.data.network.services.car.fillup

import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpCreate
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface FillUpService {
    suspend fun getAll(): Results<List<FillUpDto>, DataError.Network>
    suspend fun save(
        fillUp: FillUpCreate,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>
}