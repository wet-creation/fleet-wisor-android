package ua.com.fleetwisor.features.drivers.presentation.edit

import android.content.Context
import android.net.Uri
import java.time.LocalDate

sealed interface DriversEditAction {
    data object NavigateBack : DriversEditAction
    data object DismissDialog: DriversEditAction
    data object ConfirmDeleteDriver: DriversEditAction
    data object DismissErrorDialog : DriversEditAction


    data class SelectFrontPhoto(val photo: Uri) : DriversEditAction
    data class SaveDriver(val context: Context) : DriversEditAction
    data class SelectBackPhoto(val photo: Uri) : DriversEditAction
    data class InputSurname(val value: String) : DriversEditAction
    data class InputName(val value: String) : DriversEditAction
    data class InputPhoneNumber(val value: String) : DriversEditAction
    data class SelectBirthDay(val value: LocalDate) : DriversEditAction
    data class InputSalary(val value: String) : DriversEditAction
    data class InputLicenseNumber(val value: String) : DriversEditAction

}