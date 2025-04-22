package ua.com.fleetwisor.features.profile.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.profile.domain.models.Owner

interface ProfileRepository {

    suspend fun getUser(): Results<Owner, DataError.Network>

    suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun changeInfo(
        newOwner: Owner
    ): Results<Owner, DataError.Network>

}