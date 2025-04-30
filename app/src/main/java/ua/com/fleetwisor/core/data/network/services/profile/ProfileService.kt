package ua.com.fleetwisor.core.data.network.services.profile

import ua.com.fleetwisor.core.data.network.services.profile.dto.OwnerDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsUpdate
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface ProfileService {

    suspend fun getUser(): Results<OwnerDto, DataError.Network>

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun changeInfo(
        newOwner: OwnerDto
    ): Results<OwnerDto, DataError.Network>

    suspend fun getUserSettings(): Results<UserSettingsDto, DataError.Network>
    suspend fun saveUserSettings(userSettings: UserSettingsUpdate): EmptyDataAndErrorResult<DataError.Network>
}