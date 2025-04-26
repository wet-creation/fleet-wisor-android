package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.main.CarService
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarBodyDto
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.data.network.services.car.main.dto.FuelTypeDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.drivers.data.asDriver

class CarRepositoryImpl(
    private val carService: CarService
) : CarRepository {
    override suspend fun getAll(): Results<List<Car>, DataError.Network> {
        return carService.getAll().mapData { it?.map { it.asCar() } ?: emptyList() }
    }
}

fun CarDto.asCar(): Car {

    return Car(
        id = id,
        brandName = brandName,
        color = color ?: "",
        vin = vin ?: "",
        model = model ?: "",
        licensePlate = licensePlate ?: "",
        mileAge = mileAge,
        fuelTypes = fuelTypes.map { it.asFuelType() },
        carBody = carBody.asCarBody(),
        drivers = drivers.map { it.asDriver() }
    )
}

private fun FuelTypeDto.asFuelType() = FuelType(
    id = id,
    name = name
)

private fun CarBodyDto.asCarBody() = CarBody(
    id = id,
    name = name
)


