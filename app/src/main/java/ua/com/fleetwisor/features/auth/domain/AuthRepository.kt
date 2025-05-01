package ua.com.fleetwisor.features.auth.domain

import kotlinx.coroutines.flow.Flow
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo
import ua.com.fleetwisor.features.auth.domain.models.RegisterInfo

interface AuthRepository {

    suspend fun login(email: String, password: String): EmptyDataAndErrorResult<DataError.Network>
    suspend fun register(
        registerInfo: RegisterInfo,
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun refreshToken(): EmptyDataAndErrorResult<DataError.Network>

    fun authInfo(): Flow<AuthInfo>

}