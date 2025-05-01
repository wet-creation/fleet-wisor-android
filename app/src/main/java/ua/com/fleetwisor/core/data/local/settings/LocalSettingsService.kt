package ua.com.fleetwisor.core.data.local.settings

import kotlinx.coroutines.flow.Flow
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

interface LocalSettingsService {

    suspend fun saveFuelType(map: Map<Int, FuelUnits>)
    suspend fun getFuelTypeSelectedUnit(fuelTypeId: Int): Flow<FuelUnits>
}