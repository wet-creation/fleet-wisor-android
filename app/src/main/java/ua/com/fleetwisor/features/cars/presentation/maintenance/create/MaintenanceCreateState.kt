package ua.com.fleetwisor.features.cars.presentation.maintenance.create

import android.net.Uri
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceCreateState(
    val selectedTab: Int = 0,
    val maintenance: Maintenance = Maintenance(),
    val cars: List<Car> = emptyList(),
    val selectedPhoto: Uri? = null,
    val error: UiText = emptyUiText
)