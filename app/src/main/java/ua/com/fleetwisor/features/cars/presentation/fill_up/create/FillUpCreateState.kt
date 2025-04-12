package ua.com.fleetwisor.features.cars.presentation.fill_up.create

import ua.com.fleetwisor.features.cars.domain.models.FillUp

data class FillUpCreateState(
    val fillUp: FillUp = FillUp(),
    val selectedTab: Int = 0
)