package ua.com.fleetwisor.features.drivers.presentation.create

import android.content.Context
import android.net.Uri
import java.time.LocalDate

sealed interface DriverCreateAction {
    data object NavigateBack : DriverCreateAction
    data object OpenDateSelector : DriverCreateAction
    data object DismissDateSelector : DriverCreateAction
    data object DismissErrorDialog : DriverCreateAction

    data class SelectFrontPhoto(val photo: Uri) : DriverCreateAction
    data class SaveDriver(val context: Context) : DriverCreateAction
    data class SelectBackPhoto(val photo: Uri) : DriverCreateAction
    data class InputSurname(val value: String) : DriverCreateAction
    data class InputName(val value: String) : DriverCreateAction
    data class InputPhoneNumber(val value: String) : DriverCreateAction
    data class SelectBirthDay(val value: LocalDate) : DriverCreateAction
    data class InputSalary(val value: String) : DriverCreateAction
    data class InputLicenseNumber(val value: String) : DriverCreateAction


}