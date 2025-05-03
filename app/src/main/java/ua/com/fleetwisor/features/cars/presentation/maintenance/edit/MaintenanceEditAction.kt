package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import android.content.Context
import android.net.Uri
import java.time.LocalDateTime

sealed interface MaintenanceEditAction {
    companion object : MaintenanceEditAction
    data object NavigateBack : MaintenanceEditAction
    data object Delete : MaintenanceEditAction
    data class ChangeTabIndex(val index: Int) : MaintenanceEditAction
    data class SelectTimeDate(val time: LocalDateTime) : MaintenanceEditAction
    data class InputPrice(val value: Double) : MaintenanceEditAction
    data class InputDescription(val value: String) : MaintenanceEditAction
    data class SelectPhoto(val photo: Uri) : MaintenanceEditAction
    data class Save(val context: Context) : MaintenanceEditAction
}