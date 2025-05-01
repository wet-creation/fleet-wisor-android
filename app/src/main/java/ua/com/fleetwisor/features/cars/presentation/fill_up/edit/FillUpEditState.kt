package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import ua.com.fleetwisor.features.cars.domain.models.FillUp

data class FillUpEditState(
    val fillUp: FillUp = FillUp(),
    val selectedTab: Int = 0
)