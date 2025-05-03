package ua.com.fleetwisor.core.data.network.services.car.maintenance

import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceCreate
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceUpdate
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface MaintenanceService {
    suspend fun getAll(): Results<List<MaintenanceDto>, DataError.Network>
    suspend fun get(id: Int): Results<MaintenanceDto, DataError.Network>
    suspend fun delete(id: Int): EmptyDataAndErrorResult<DataError.Network>
    suspend fun save(
        maintenanceCreate: MaintenanceCreate,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun edit(
        maintenanceCreate: MaintenanceUpdate,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>

}