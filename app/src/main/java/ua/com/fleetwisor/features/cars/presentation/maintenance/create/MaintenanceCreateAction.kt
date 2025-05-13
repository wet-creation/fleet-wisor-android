package ua.com.fleetwisor.features.cars.presentation.maintenance.create

import android.content.Context
import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.Index
import java.time.LocalDateTime


sealed interface MaintenanceCreateAction {
    companion object: MaintenanceCreateAction
    data object NavigateBack : MaintenanceCreateAction
    data object DismissErrorDialog : MaintenanceCreateAction
    data class ChangeTabIndex(val index: Int) : MaintenanceCreateAction
    data class SelectTimeDate(val time: LocalDateTime) : MaintenanceCreateAction
    data class InputPrice(val value: Double) : MaintenanceCreateAction
    data class InputDescription(val value: String) : MaintenanceCreateAction
    data class SelectedCarIndex(val car: Index) : MaintenanceCreateAction
    data class SelectPhoto(val photo: Uri) : MaintenanceCreateAction
    data class Save(val context: Context) : MaintenanceCreateAction

}