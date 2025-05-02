package ua.com.fleetwisor.features.cars.data

import ua.com.fleetwisor.core.data.network.services.car.fillup.FillUpService
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpCreate
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpDto
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpUpdate
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

    override suspend fun get(id: Int): Results<FillUp, DataError.Network> {
        return fillUpService.get(id).mapData { it?.asFillUp() ?: FillUp() }
    }

    override suspend fun save(
        fillUp: FillUp,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return fillUpService.save(fillUp.asFillUpCreate(), photo)
    }

    override suspend fun delete(fillUpId: Int): EmptyDataAndErrorResult<DataError.Network> {
        return fillUpService.delete(fillUpId)
    }

    override suspend fun edit(
        fillUp: FillUp,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return fillUpService.edit(fillUp.asFillUpUpdate(), photo)
    }
}

private fun FillUp.asFillUpUpdate(): FillUpUpdate {
    return FillUpUpdate(
        id = id,
        time = time.toString(),
        price = price,
        checkUrl = checkUrl ?: "",
        amount = amount
    )
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
        fuelType = fuelType.asFuelType(),
        fuelUnits = unit.asUnits(),
        car = car.asCar()
    )
}
