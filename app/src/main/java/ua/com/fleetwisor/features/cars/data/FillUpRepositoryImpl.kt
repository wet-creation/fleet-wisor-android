package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.fillup.FillUpService
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpCreate
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.cars.domain.FillUpRepository
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import java.time.LocalDateTime

class FillUpRepositoryImpl(
    private val fillUpService: FillUpService
) : FillUpRepository {
    override suspend fun getAll(): Results<List<FillUp>, DataError.Network> {
        return fillUpService.getAll().mapData { it?.map { it.asFillUp() } ?: emptyList() }
    }

    override suspend fun save(
        fillUp: FillUp,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return fillUpService.save(fillUp.asFillUpCreate(), photo)
    }
}

private fun FillUp.asFillUpCreate(): FillUpCreate {
    return FillUpCreate(
        time = time.toString(),
        price = price,
        carId = car.id,
        unitId = fuelUnits.id,
        amount = amount
    )
}

private fun FillUpDto.asFillUp(): FillUp {

    return FillUp(
        id = id,
        time = LocalDateTime.parse(time),
        price = price,
        checkUrl = checkUrl,
        amount = amount,
        car = car.asCar()
    )
}
