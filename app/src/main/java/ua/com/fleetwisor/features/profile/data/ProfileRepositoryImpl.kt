package ua.com.fleetwisor.features.profile.data

import ua.com.fleetwisor.core.data.local.settings.LocalSettingsService
import ua.com.fleetwisor.core.data.network.services.common.FuelTypeNCarBodyService
import ua.com.fleetwisor.core.data.network.services.common.dto.UnitsUpdate
import ua.com.fleetwisor.core.data.network.services.profile.ProfileService
import ua.com.fleetwisor.core.data.network.services.profile.dto.OwnerDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsUpdate
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.profile.domain.ProfileRepository
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits
import ua.com.fleetwisor.features.profile.domain.models.Owner
import ua.com.fleetwisor.features.profile.domain.models.UserSettings

class ProfileRepositoryImpl(
    private val profileService: ProfileService,
    private val localSettingsService: LocalSettingsService,
    private val fuelTypeService: FuelTypeNCarBodyService
) : ProfileRepository {
    override suspend fun getUser(): Results<Owner, DataError.Network> {
        return profileService.getUser().mapData { it?.asOwner() ?: Owner() }
    }

    override suspend fun getAllFuelTypes(): Results<List<FuelType>, DataError.Network> {
        return fuelTypeService.getAllFuelTypes()
            .mapData { it?.map { it.asFuelType() } ?: emptyList() }
    }

    override suspend fun getUserSettings(): Results<UserSettings, DataError.Network> {
        return profileService.getUserSettings().mapData { it?.asUserSettings() ?: UserSettings() }
    }

    override suspend fun saveUserSettings(userSettings: Map<Int, FuelUnits>): EmptyDataAndErrorResult<DataError.Network> {
        val res = profileService.saveUserSettings(
            userSettings = UserSettingsUpdate(
                fuelUnits = userSettings.map { (fuel, unit) ->
                    UnitsUpdate(
                        idFuelType = fuel,
                        idUnit = unit.id
                    )
                }
            ))
        if (res is FullResult.Success)
            localSettingsService.saveFuelType(userSettings)

        return res
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network> {
        return profileService.changePassword(oldPassword, newPassword)
    }

    override suspend fun changeInfo(newOwner: Owner): Results<Owner, DataError.Network> {
        return profileService.changeInfo(newOwner.asOwnerDto()).mapData { it?.asOwner() ?: Owner() }

    }
}

private fun UserSettingsDto.asUserSettings(): UserSettings {

    return UserSettings(
        fuelUnits = fuelUnits.map { it.asUnits() }
    )
}

private fun Owner.asOwnerDto(): OwnerDto {
    return OwnerDto(
        id = id,
        email = email,
        name = name,
        surname = surname
    )
}

private fun OwnerDto.asOwner(): Owner {
    return Owner(
        id = id,
        email = email,
        name = name,
        surname = surname
    )
}
