package ua.com.fleetwisor.features.drivers.presentation.edit

import android.net.Uri
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.drivers.domain.models.Driver

data class DriversEditState(
    val driver: Driver = Driver(),
    val editDriver: Driver = Driver(),
    val inputSalary: String = "",
    val selectedFrontPhoto: Uri? = null,
    val selectedBackPhoto: Uri? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val error: UiText = emptyUiText
)