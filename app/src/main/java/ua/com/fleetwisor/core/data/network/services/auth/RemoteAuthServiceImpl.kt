package ua.com.fleetwisor.core.data.network.services.auth

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.post
import ua.com.fleetwisor.core.data.network.services.auth.dto.AuthInfoDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.LoginDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.RegisterDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.TokenRequest
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val postLogin = "/api/v1/auth/login"
private const val postRegister = "/api/v1/auth/register"
private const val postRefresh = "/api/v1/auth/update/refresh"

class RemoteAuthServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : RemoteAuthService {
    override suspend fun login(
        email: String,
        password: String
    ): Results<AuthInfoDto, DataError.Network> {
        return httpClientFactory.getClient().post<LoginDto, AuthInfoDto, Unit>(
            postLogin,
            LoginDto(
                email,
                password
            )
        )
    }

    override suspend fun register(registerInfo: RegisterDto): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().post<RegisterDto, Unit, Unit>(
            postRegister,
            registerInfo
        )
    }

    override suspend fun refreshToken(refreshToken: String): Results<AuthInfoDto, DataError.Network> {
        return httpClientFactory.getClient().post<TokenRequest, AuthInfoDto, Unit>(
            postRefresh,
            TokenRequest(refreshToken)
        )
    }
}