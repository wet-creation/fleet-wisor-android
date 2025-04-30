package ua.com.fleetwisor.features.profile.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.profile.domain.models.Owner
import ua.com.fleetwisor.features.profile.domain.models.UserSettings

interface ProfileRepository {

    suspend fun getUser(): Results<Owner, DataError.Network>

    suspend fun getAllFuelTypes(): Results<List<FuelType>, DataError.Network>
    suspend fun getUserSettings(): Results<UserSettings, DataError.Network>
    suspend fun saveUserSettings(userSettings: Map<Int, Int>): EmptyDataAndErrorResult<DataError.Network>

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun changeInfo(
        newOwner: Owner
    ): Results<Owner, DataError.Network>

}