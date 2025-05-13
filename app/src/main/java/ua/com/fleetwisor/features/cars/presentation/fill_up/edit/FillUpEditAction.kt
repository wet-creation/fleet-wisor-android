package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import android.content.Context
import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.Index
import java.time.LocalDateTime

sealed interface FillUpEditAction {
    companion object : FillUpEditAction
    data object NavigateBack : FillUpEditAction

    data class ChangeTabIndex(val index: Int) : FillUpEditAction
    data class SelectPhoto(val uri: Uri) : FillUpEditAction
    data object Delete : FillUpEditAction
    data object DismissErrorDialog : FillUpEditAction

    data class SelectedCarIndex(val car: Index) : FillUpEditAction
    data class InputPrice(val value: String) : FillUpEditAction
    data class InputAmount(val value: String) : FillUpEditAction

    data class SelectFuelType(val id: Int) : FillUpEditAction
    data class Save(val context: Context) : FillUpEditAction
    data class SelectTimeDate(val dateTime: LocalDateTime) : FillUpEditAction
}