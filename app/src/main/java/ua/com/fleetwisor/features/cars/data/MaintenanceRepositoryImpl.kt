package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.maintenance.MaintenanceService
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
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
