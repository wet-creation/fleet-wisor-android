package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.maintenance.MaintenanceService
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceCreate
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceUpdate
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.cars.domain.MaintenanceRepository
import ua.com.fleetwisor.features.cars.domain.models.Maintenance
import java.time.LocalDateTime

class MaintenanceRepositoryImpl(
    private val service: MaintenanceService
) : MaintenanceRepository {
    override suspend fun getAll(): Results<List<Maintenance>, DataError.Network> {
        return service.getAll().mapData { it?.map { it.asMaintenance() } ?: emptyList() }
    }

    override suspend fun get(id: Int): Results<Maintenance, DataError.Network> {
        return service.get(id).mapData { it?.asMaintenance() ?: Maintenance() }
    }

    override suspend fun save(
        maintenance: Maintenance,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return service.save(maintenance.asMaintenanceCreate(), photo)
    }

    override suspend fun delete(maintenanceId: Int): EmptyDataAndErrorResult<DataError.Network> {
        return service.delete(maintenanceId)
    }

    override suspend fun edit(
        maintenance: Maintenance,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return service.edit(maintenance.asMaintenanceUpdate(), photo)
    }
}

private fun Maintenance.asMaintenanceUpdate(): MaintenanceUpdate {
    return MaintenanceUpdate(
        time = time.toString(),
        description = description,
        id = id,
        checkUrl = checkUrl ?: "",
        price = price
    )

}

private fun Maintenance.asMaintenanceCreate(): MaintenanceCreate {
    return MaintenanceCreate(
        time = time.toString(),
        description = description,
        carId = car.id,
        price = price
    )
}

private fun MaintenanceDto.asMaintenance(): Maintenance {
    return Maintenance(
        id = id,
        time = LocalDateTime.parse(time),
        description = description,
        checkUrl = checkUrl,
        car = car.asCar(),
        price = price
    )
}
