package ua.com.fleetwisor.features.cars.presentation.fill_up.create

import android.net.Uri
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.FillUp
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

data class FillUpCreateState(
    val fillUp: FillUp = FillUp(),
    val cars: List<Car> = emptyList(),
    val fuelTypes: Set<FuelType> = emptySet(),
    val userUnits: List<FuelUnits> = emptyList(),
    val selectedTab: Int = 0,
    val selectedCarIndex: Int = 0,
    val selectedPhoto: Uri? = null,
    val isLoading: Boolean = false,
    val error: UiText = emptyUiText,
)