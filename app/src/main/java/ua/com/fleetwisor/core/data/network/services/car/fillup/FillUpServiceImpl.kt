package ua.com.fleetwisor.core.data.network.services.car.fillup

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "api/v1/cars/fill-up"

class FillUpServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : FillUpService {
    override suspend fun getAll(): Results<List<FillUpDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }
}