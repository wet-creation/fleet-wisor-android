package ua.com.fleetwisor.core.data.network.services.car.main

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "/api/v1/cars"

class CarServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : CarService {
    override suspend fun getAll(): Results<List<CarDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }
}