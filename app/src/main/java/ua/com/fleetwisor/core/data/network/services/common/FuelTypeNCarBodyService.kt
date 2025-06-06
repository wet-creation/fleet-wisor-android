package ua.com.fleetwisor.core.data.network.services.common

import ua.com.fleetwisor.core.data.network.services.common.dto.CarBodyDto
import ua.com.fleetwisor.core.data.network.services.common.dto.FuelTypeDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

interface FuelTypeNCarBodyService {

     suspend fun getAllFuelTypes(): Results<List<FuelTypeDto>, DataError.Network>
     suspend fun getAllCarsBody(): Results<List<CarBodyDto>, DataError.Network>
}