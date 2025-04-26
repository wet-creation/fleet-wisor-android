package ua.com.fleetwisor.core.data.network.services.car.maintenance

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "/api/v1/cars/maintenance"

class MaintenanceServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : MaintenanceService {
    override suspend fun getAll(): Results<List<MaintenanceDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }
}