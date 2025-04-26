package ua.com.fleetwisor.core.data.network.services.car.main

import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results

interface CarService {

    suspend fun getAll(): Results<List<CarDto>, DataError.Network>
}