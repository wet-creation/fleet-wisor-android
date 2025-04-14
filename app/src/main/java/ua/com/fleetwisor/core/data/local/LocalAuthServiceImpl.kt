package ua.com.fleetwisor.core.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo

class LocalAuthServiceImpl(
    private val context: Context,
) : LocalAuthService {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val refreshToken = stringPreferencesKey("refreshToken")
    private val accessToken = stringPreferencesKey("accessToken")

    override suspend fun saveAuthInfo(authInfo: AuthInfo) {
        context.dataStore.edit {
            it[refreshToken] = authInfo.refreshToken
            it[accessToken] = authInfo.accessToken
        }
    }

    override fun getAuthInfo(): Flow<AuthInfo> {
        return context.dataStore.data.map {
            AuthInfo(
                refreshToken = it[refreshToken] ?: "",
                accessToken = it[accessToken] ?: ""
            )
        }
    }
}