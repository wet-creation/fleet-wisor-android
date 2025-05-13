package ua.com.fleetwisor.features.cars.presentation.fill_up.create

import android.content.Context
import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.Index
import java.time.LocalDateTime

sealed interface FillUpCreateAction {

    companion object : FillUpCreateAction
    data object NavigateBack : FillUpCreateAction
    data object DismissErrorDialog : FillUpCreateAction

    data class SelectPhoto(val uri: Uri) : FillUpCreateAction
    data class SelectedCarIndex(val car: Index) : FillUpCreateAction
    data class InputPrice(val value: String) : FillUpCreateAction

    data class InputAmount(val value: String) : FillUpCreateAction
    data class SelectFuelType(val id: Int) : FillUpCreateAction
    data class Save(val context: Context) : FillUpCreateAction
    data class SelectTimeDate(val dateTime: LocalDateTime) : FillUpCreateAction
    data class ChangeTabIndex(val index: Int) : FillUpCreateAction
}