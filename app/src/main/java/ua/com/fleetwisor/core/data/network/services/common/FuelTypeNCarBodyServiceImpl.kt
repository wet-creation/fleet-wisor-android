package ua.com.fleetwisor.core.data.network.services.common

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.common.dto.CarBodyDto
import ua.com.fleetwisor.core.data.network.services.common.dto.FuelTypeDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAllFuelType = "/api/v1/{lang}/cars/fuel-type"
private const val getAllCarBody = "/api/v1/{lang}/cars/car-body"

class FuelTypeNCarBodyServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : FuelTypeNCarBodyService {
    override suspend fun getAllFuelTypes(): Results<List<FuelTypeDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAllFuelType)
    }

    override suspend fun getAllCarsBody(): Results<List<CarBodyDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAllCarBody)

    }
}