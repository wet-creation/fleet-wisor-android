package ua.com.fleetwisor.features.cars.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.domain.models.Insurance

interface CarRepository {

    suspend fun getAll(): Results<List<Car>, DataError.Network>

    suspend fun getAllFuelType(): Results<List<FuelType>, DataError.Network>
    suspend fun getAllCarBody(): Results<List<CarBody>, DataError.Network>
    suspend fun saveCar(
        car: Car,
        insurance: Insurance? = null,
        photo: Pair<String, ByteArray>? = null
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun getCar(id: Int): Results<Car, DataError.Network>
    suspend fun editCar(
        editCar: Car
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun delete(carId: Int): EmptyDataAndErrorResult<DataError.Network>
    suspend fun editInsurance(
        carId: Int,
        editInsurance: Insurance,
        photo: Pair<String, ByteArray>? = null
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun getInsurance(carId: Int): Results<Insurance, DataError.Network>
}