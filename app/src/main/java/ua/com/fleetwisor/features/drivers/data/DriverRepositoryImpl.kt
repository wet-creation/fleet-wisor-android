package ua.com.fleetwisor.features.drivers.data

import ua.com.fleetwisor.core.data.network.services.driver.DriverService
import ua.com.fleetwisor.core.data.network.services.driver.dto.CreateDriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.EditDriverDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.drivers.domain.DriverRepository
import ua.com.fleetwisor.features.drivers.domain.models.CreateDriver
import ua.com.fleetwisor.features.drivers.domain.models.Driver
import java.time.LocalDate

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

    override suspend fun getDriver(id: Int): Results<Driver, DataError.Network> {
        return driverService.getDriver(id).mapData { it?.asDriver() ?: Driver() }
    }

    override suspend fun editDriver(
        driver: Driver,
        front: Pair<String, ByteArray>?,
        back: Pair<String, ByteArray>?
    ): Results<Driver, DataError.Network> {
        return driverService.editDriver(driver.id, driver.asDriverEditDto(), front, back)
            .mapData { it?.asDriver() ?: Driver() }
    }

    override suspend fun deleteDriver(id: Int): EmptyDataAndErrorResult<DataError.Network> {
        return driverService.deleteDriver(id)
    }
}

private fun Driver.asDriverEditDto(): EditDriverDto {
    return EditDriverDto(
        name = name,
        surname = surname,
        phone = phoneNumber,
        driverLicenseNumber = driverLicenseNumber,
        birthdayDate = birthdayDate.toString(),
        salary = salary
    )
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
        birthdayDate = LocalDate.parse(birthdayDate),
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


