package ua.com.fleetwisor.core.data.network.services.car.main

import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarCreate
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarUpdate
import ua.com.fleetwisor.core.data.network.services.car.main.dto.InsuranceDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface CarService {

    suspend fun getAll(): Results<List<CarDto>, DataError.Network>
    suspend fun get(id: Int): Results<CarDto, DataError.Network>
    suspend fun getInsurance(carId: Int): Results<InsuranceDto, DataError.Network>
    suspend fun saveCar(
        value: CarCreate,
        insurance: InsuranceDto? = null,
        photo: Pair<String, ByteArray>? = null
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun editCar(car: CarUpdate): EmptyDataAndErrorResult<DataError.Network>
    suspend fun delete(carId: Int): EmptyDataAndErrorResult<DataError.Network>
    suspend fun editInsurance(
        carId: Int,
        insurance: InsuranceDto,
        pair: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network>
}