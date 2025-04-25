package ua.com.fleetwisor.core.data.network.services.driver

import ua.com.fleetwisor.core.data.network.services.driver.dto.CreateDriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.EditDriverDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface DriverService {
    suspend fun getList(): Results<List<DriverDto>, DataError.Network>

    suspend fun createDriver(
        driver: CreateDriverDto,
        frontPhoto: Pair<String, ByteArray>?,
        backPhoto: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun getDriver(id: Int): Results<DriverDto, DataError.Network>
    suspend fun editDriver(
        id: Int,
        editDriverDto: EditDriverDto,
        frontPhoto: Pair<String, ByteArray>?,
        backPhoto: Pair<String, ByteArray>?
    ): Results<DriverDto, DataError.Network>

    suspend fun deleteDriver(
        id: Int,
    ): EmptyDataAndErrorResult<DataError.Network>


}