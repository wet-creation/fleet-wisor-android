package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import android.net.Uri
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceEditState(
    val selectedTab: Int = 0,
    val maintenance: Maintenance = Maintenance(),
    val selectedPhoto: Uri? = null,
    val error: UiText = emptyUiText
)