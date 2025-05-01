package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.main.CarService
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarCreate
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarUpdate
import ua.com.fleetwisor.core.data.network.services.car.main.dto.InsuranceDto
import ua.com.fleetwisor.core.data.network.services.common.FuelTypeNCarBodyService
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.core.domain.utils.parseDateOrNull
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.drivers.data.asDriver

class CarRepositoryImpl(
    private val carService: CarService,
    private val fuelTypeNCarBodyService: FuelTypeNCarBodyService
) : CarRepository {
    override suspend fun getAll(): Results<List<Car>, DataError.Network> {
        return carService.getAll().mapData { it?.map { it.asCar() } ?: emptyList() }
    }

    override suspend fun getAllFuelType(): Results<List<FuelType>, DataError.Network> {
        return fuelTypeNCarBodyService.getAllFuelTypes()
            .mapData { it?.map { it.asFuelType() } ?: emptyList() }
    }

    override suspend fun getAllCarBody(): Results<List<CarBody>, DataError.Network> {
        return fuelTypeNCarBodyService.getAllCarsBody()
            .mapData { it?.map { it.asCarBody() } ?: emptyList() }
    }

    override suspend fun saveCar(
        car: Car,
        insurance: Insurance?,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return carService.saveCar(car.asCarCreate(), insurance?.asInsuranceDto(), photo)
    }

    override suspend fun getCar(id: Int): Results<Car, DataError.Network> {
        return carService.get(id).mapData { it?.asCar() ?: Car() }
    }

    override suspend fun editCar(editCar: Car): EmptyDataAndErrorResult<DataError.Network> {
        return carService.editCar(editCar.asCarUpdateDto())

    }

    override suspend fun delete(
        carId: Int,
    ): EmptyDataAndErrorResult<DataError.Network> {
        return carService.delete(carId)
    }

    override suspend fun editInsurance(
        carId: Int,
        editInsurance: Insurance,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return carService.editInsurance(carId, editInsurance.asInsuranceDto(), photo)

    }

    override suspend fun getInsurance(carId: Int): Results<Insurance, DataError.Network> {
        return carService.getInsurance(carId).mapData { it?.asInsurance() ?: Insurance() }

    }
}

private fun Car.asCarUpdateDto(): CarUpdate {
    return CarUpdate(
        id = id,
        brandName = brandName.trim(),
        color = if (color.isNotEmptyOrBlank()) color.trim() else null,
        vin = if (vin.isNotEmptyOrBlank()) vin.trim() else null,
        model = model.trim(),
        licensePlate = licensePlate.trim(),
        mileAge = mileAge,
        drivers = drivers.map { it.id },
        fuelTypes = fuelTypes.map { it.id },
        carBodyId = carBody.id
    )
}

private fun InsuranceDto.asInsurance(): Insurance = Insurance(
    startDate = startDate.parseDateOrNull(),
    endDate = endDate.parseDateOrNull(),
    carId = carId,
    photoUrl = photoUrl,
    id = id
)

private fun Insurance.asInsuranceDto(): InsuranceDto {
    return InsuranceDto(
        id = id,
        photoUrl = photoUrl,
        startDate = startDate.toString(),
        endDate = endDate.toString(),
    )
}

private fun Car.asCarCreate(): CarCreate {
    return CarCreate(
        brandName = brandName.trim(),
        color = if (color.isNotEmptyOrBlank()) color.trim() else null,
        vin = if (vin.isNotEmptyOrBlank()) vin.trim() else null,
        model = model.trim(),
        licensePlate = licensePlate.trim(),
        mileAge = mileAge,
        drivers = drivers.map { it.id },
        fuelTypes = fuelTypes.map { it.id },
        carBodyId = carBody.id
    )
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
        fuelTypes = fuelTypes.map { it.asFuelType() }.toSet(),
        carBody = carBody.asCarBody(),
        drivers = drivers.map { it.asDriver() }
    )
}



