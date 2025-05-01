package ua.com.fleetwisor.features.drivers.presentation.create

import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import java.time.LocalDate

data class DriverCreateState(
    val inputName: String = "",
    val inputSurname: String = "",
    val inputPhone: String = "",
    val isDateSelectorOpen: Boolean = false,
    val selectedBirthDay: LocalDate = LocalDate.now(),
    val inputBirthDay: String = "",
    val inputSalary: String = "",
    val salary: Double = 0.0,
    val error: UiText = emptyUiText,
    val inputLicenseNumber: String = "",
    val frontPhoto: Uri? = null,
    val backPhoto: Uri? = null,
    val savingInProgress: Boolean = false
) {
    val canBeCreated
        get() = listOf(
            inputName,
            inputSalary,
            inputPhone,
            inputSurname,
            inputBirthDay,
            inputLicenseNumber
        ).isNotEmptyOrBlank()

}