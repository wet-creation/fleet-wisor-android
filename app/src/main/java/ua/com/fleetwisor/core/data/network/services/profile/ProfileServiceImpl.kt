package ua.com.fleetwisor.core.data.network.services.profile

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.put
import ua.com.fleetwisor.core.data.network.services.profile.dto.ChangePassword
import ua.com.fleetwisor.core.data.network.services.profile.dto.OwnerDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

const val getOwner = "/api/v1/owners/me"
const val putInfo = "/api/v1/owners/update/info"
const val putPassword = "/api/v1/owners/update/password"

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
}