package ua.com.fleetwisor.features.cars.presentation.cars.create

import android.content.Context
import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.Index

sealed interface CarCreateAction {
    data object NavigateBack : CarCreateAction
    data object AddFuelType : CarCreateAction


    data class SelectPeriod(val start: Long, val end: Long) : CarCreateAction
    data class SaveCar(val context: Context) : CarCreateAction
    data class InputModel(val value: String) : CarCreateAction
    data class InputColor(val value: String) : CarCreateAction
    data class InputBrandName(val value: String) : CarCreateAction
    data class InputOdometer(val value: String) : CarCreateAction
    data class InputVin(val value: String) : CarCreateAction
    data class InputLicensePlate(val value: String) : CarCreateAction
    data class SearchDriver(val value: String) : CarCreateAction
    data class SelectDriver(val drivers: List<Index>) : CarCreateAction
    data class SelectCarBody(val carBodyId: Int) : CarCreateAction
    data class SelectFuelType(val index: Index, val fuelTypeId: Int) : CarCreateAction
    data class DeleteFuelType(val fuelTypeIndex: Index) : CarCreateAction
    data class ChangeTabIndex(val index: Index) : CarCreateAction
    data class SelectPhoto(val uri: Uri) : CarCreateAction
    companion object : CarCreateAction
}