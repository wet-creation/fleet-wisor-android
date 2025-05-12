package ua.com.fleetwisor.core.data.network.services.profile

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.put
import ua.com.fleetwisor.core.data.network.services.profile.dto.ChangePassword
import ua.com.fleetwisor.core.data.network.services.profile.dto.OwnerDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsDto
import ua.com.fleetwisor.core.data.network.services.profile.dto.UserSettingsUpdate
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getOwner = "/api/v1/{lang}/owners/me"
private const val getOwnerSettings = "/api/v1/{lang}/owners/me/settings"
private const val putInfo = "/api/v1/{lang}/owners/update/info"
private const val putPassword = "/api/v1/{lang}/owners/update/password"
private const val putUserSettings = "/api/v1/{lang}/owners/update/settings"

class ProfileServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : ProfileService {
    override suspend fun getUser(): Results<OwnerDto, DataError.Network> {
        return httpClientFactory.getClient().get(getOwner)
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().put(
            putPassword, ChangePassword(
                oldPassword, newPassword
            )
        )

    }

    override suspend fun changeInfo(newOwner: OwnerDto): Results<OwnerDto, DataError.Network> {
        return httpClientFactory.getClient().put(
            putInfo, newOwner
        )
    }

    override suspend fun getUserSettings(): Results<UserSettingsDto, DataError.Network> {
        return httpClientFactory.getClient().get(getOwnerSettings)
    }

    override suspend fun saveUserSettings(userSettings: UserSettingsUpdate): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().put(putUserSettings, userSettings)
    }
}