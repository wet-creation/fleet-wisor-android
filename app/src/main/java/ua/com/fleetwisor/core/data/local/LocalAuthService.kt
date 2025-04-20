package ua.com.fleetwisor.core.data.local

import kotlinx.coroutines.flow.Flow
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo

interface LocalAuthService {
    suspend fun saveAuthInfo(authInfo: AuthInfo)
    fun getAuthInfo(): Flow<AuthInfo>
   suspend fun logout()
}