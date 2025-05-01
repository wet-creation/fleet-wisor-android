package ua.com.fleetwisor.core.data.network.services.auth

import ua.com.fleetwisor.core.data.network.services.auth.dto.AuthInfoDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.RegisterDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

interface RemoteAuthService {

    suspend fun login(email: String, password: String): Results<AuthInfoDto, DataError.Network>
    suspend fun register(
        registerInfo: RegisterDto
    ): EmptyDataAndErrorResult<DataError.Network>

    suspend fun refreshToken(refreshToken: String): Results<AuthInfoDto, DataError.Network>
}