package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import android.net.Uri
import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.FillUp

data class FillUpEditState(
    val fillUp: FillUp = FillUp(),
    val cars: List<Car> = listOf(),
    val selectedTab: Int = 0,
    val selectedCarIndex: Int = 0,
    val selectedPhoto: Uri? = null
)