package ua.com.fleetwisor.features.drivers.data

import ua.com.fleetwisor.core.data.network.services.driver.DriverService
import ua.com.fleetwisor.core.data.network.services.driver.dto.CreateDriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.drivers.domain.DriverRepository
import ua.com.fleetwisor.features.drivers.domain.models.CreateDriver
import ua.com.fleetwisor.features.drivers.domain.models.Driver

class DriverRepositoryImpl(
    private val driverService: DriverService
) : DriverRepository {
    override suspend fun getList(): Results<List<Driver>, DataError.Network> {
        return driverService.getList().mapData { it?.map { it.asDriver() } ?: emptyList() }
    }

    override suspend fun createDriver(
        driver: CreateDriver,
        front: Pair<String, ByteArray>?,
        back: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        return driverService.createDriver(driver.asDriverCreateDto(), front, back)
    }
}

private fun DriverDto.asDriver(): Driver {
    return Driver(
        id = id,
        name = name,
        surname = surname,
        phoneNumber = phone,
        driverLicenseNumber = driverLicenseNumber,
        frontLicensePhotoUrl = frontLicensePhotoUrl,
        backLicensePhotoUrl = backLicensePhotoUrl,
        birthdayDate = birthdayDate,
        salary = salary
    )
}

private fun CreateDriver.asDriverCreateDto(): CreateDriverDto {
    return CreateDriverDto(
        name = name,
        surname = surname,
        phone = phoneNumber,
        driverLicenseNumber = driverLicenseNumber,
        birthdayDate = birthdayDate.toString(),
        salary = salary
    )
}


