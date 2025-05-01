package ua.com.fleetwisor.core.data.local.settings

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.com.fleetwisor.core.data.local.dataStore
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

class LocalSettingsServiceImpl(
    private val context: Context,

    ) : LocalSettingsService {
    private val fuelUnitId = "fuelUnitId-"
    private val fuelUnitName = "fuelUnitName-"

    override suspend fun saveFuelType(map: Map<Int, FuelUnits>) {
        map.forEach { (fuelId, fuelUnit) ->
            val fueUnitIdPr = intPreferencesKey(fuelUnitId + fuelId)
            val fueUnitNamePr = stringPreferencesKey(fuelUnitName + fuelId)
            context.dataStore.edit {
                it[fueUnitIdPr] = fuelUnit.id
                it[fueUnitNamePr] = fuelUnit.name
            }
        }
    }

    override suspend fun getFuelTypeSelectedUnit(fuelTypeId: Int): Flow<FuelUnits> {
        val fueUnitIdPr = intPreferencesKey(fuelUnitId + fuelTypeId)
        val fueUnitNamePr = stringPreferencesKey(fuelUnitName + fuelTypeId)
        return context.dataStore.data.map {
            FuelUnits(
                id = it[fueUnitIdPr] ?: -1,
                name = it[fueUnitNamePr] ?: "",
            )
        }
    }
}