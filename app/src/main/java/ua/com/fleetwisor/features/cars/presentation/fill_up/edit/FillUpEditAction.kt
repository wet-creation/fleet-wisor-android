package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import android.net.Uri

sealed interface FillUpEditAction {
    companion object : FillUpEditAction
    data object NavigateBack : FillUpEditAction

    data class ChangeTabIndex(val index: Int) : FillUpEditAction
    data class SelectPhoto(val uri: Uri) : FillUpEditAction
}