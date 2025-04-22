package ua.com.fleetwisor.features.profile.data

import ua.com.fleetwisor.core.data.network.services.profile.ProfileService
import ua.com.fleetwisor.core.data.network.services.profile.dto.OwnerDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.profile.domain.ProfileRepository
import ua.com.fleetwisor.features.profile.domain.models.Owner

class ProfileRepositoryImpl(
    private val profileService: ProfileService
) : ProfileRepository {
    override suspend fun getUser(): Results<Owner, DataError.Network> {
        return profileService.getUser().mapData { it?.asOwner() ?: Owner() }
    }

    override suspend fun changePassword(
        oldPassword: String,
        newPassword: String
    ): EmptyDataAndErrorResult<DataError.Network> {
        return profileService.changePassword(oldPassword, newPassword)
    }

    override suspend fun changeInfo(newOwner: Owner): Results<Owner, DataError.Network> {
        return profileService.changeInfo(newOwner.asOwnerDto()).mapData { it?.asOwner() ?: Owner() }

    }
}

private fun Owner.asOwnerDto(): OwnerDto {
    return OwnerDto(
        id = id,
        email = email,
        name = name,
        surname = surname
    )
}

private fun OwnerDto.asOwner(): Owner {
    return Owner(
        id = id,
        email = email,
        name = name,
        surname = surname
    )
}
