package ua.com.fleetwisor.features.auth.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import ua.com.fleetwisor.core.data.local.LocalAuthService
import ua.com.fleetwisor.core.data.network.services.auth.RemoteAuthService
import ua.com.fleetwisor.core.data.network.services.auth.dto.AuthInfoDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.RegisterDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.network.asEmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo
import ua.com.fleetwisor.features.auth.domain.models.RegisterInfo

class AuthRepositoryImpl(
    private val remoteAuthService: RemoteAuthService,
    private val localAuthService: LocalAuthService,
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): EmptyDataAndErrorResult<DataError.Network> {
        val res =
            remoteAuthService.login(email, password).mapData { it?.asAuthInfo() ?: AuthInfo() }

        if (res is FullResult.Success)
            localAuthService.saveAuthInfo(res.data)

        return res.asEmptyDataAndErrorResult()


    }

    override suspend fun register(
        registerInfo: RegisterInfo,
    ): EmptyDataAndErrorResult<DataError.Network> {
        return remoteAuthService.register(registerInfo.asRegisterDto())
    }

    override suspend fun refreshToken(): EmptyDataAndErrorResult<DataError.Network> {
        val refreshToken = (localAuthService.getAuthInfo().first().refreshToken)
        if (refreshToken.isEmpty()) {
            return FullResult.Success<Unit, DataError.Network, Unit>(Unit)
                .asEmptyDataAndErrorResult()
        }
        val res =
            remoteAuthService.refreshToken(refreshToken)
                .mapData { it?.asAuthInfo() ?: AuthInfo() }

        if (res is FullResult.Success)
            localAuthService.saveAuthInfo(res.data)
        else localAuthService.saveAuthInfo(AuthInfo("", ""))

        return res.asEmptyDataAndErrorResult()
    }

    override fun authInfo(): Flow<AuthInfo> {
        return localAuthService.getAuthInfo()
    }

}


fun RegisterInfo.asRegisterDto() = RegisterDto(
    name = name,
    surname = surname,
    email = email,
    password = password
)

fun AuthInfoDto.asAuthInfo() = AuthInfo(
    refreshToken = jwtRefreshToken,
    accessToken = jwtAccessToken
)