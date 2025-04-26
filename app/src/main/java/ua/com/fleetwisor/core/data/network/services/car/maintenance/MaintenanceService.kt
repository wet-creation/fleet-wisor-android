package ua.com.fleetwisor.core.data.network.services.car.maintenance

import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

interface MaintenanceService {
    suspend fun getAll(): Results<List<MaintenanceDto>, DataError.Network>
}