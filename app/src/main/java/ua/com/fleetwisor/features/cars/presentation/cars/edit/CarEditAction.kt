package ua.com.fleetwisor.features.cars.presentation.cars.edit

import android.content.Context
import android.net.Uri
import ua.com.fleetwisor.core.domain.utils.Index

sealed interface CarEditAction {
    data object NavigateBack : CarEditAction
    data object AddFuelType : CarEditAction
    data object DeleteCar : CarEditAction


    data class SelectPeriod(val start: Long, val end: Long) : CarEditAction
    data class EditCar(val context: Context) : CarEditAction
    data class InputModel(val value: String) : CarEditAction
    data class InputColor(val value: String) : CarEditAction
    data class InputBrandName(val value: String) : CarEditAction
    data class InputOdometer(val value: String) : CarEditAction
    data class InputVin(val value: String) : CarEditAction
    data class InputLicensePlate(val value: String) : CarEditAction
    data class SearchDriver(val value: String) : CarEditAction
    data class SelectDriver(val drivers: List<Index>) : CarEditAction
    data class SelectCarBody(val carBodyId: Int) : CarEditAction
    data class SelectFuelType(val index: Index, val fuelTypeId: Int) : CarEditAction
    data class DeleteFuelType(val fuelTypeIndex: Index) : CarEditAction
    data class SelectPhoto(val uri: Uri) : CarEditAction
    data class ChangeTabIndex(val index: Int) : CarEditAction
    companion object : CarEditAction
}