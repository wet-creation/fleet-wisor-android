package ua.com.fleetwisor.features.cars.presentation.cars.list

import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.models.Car

data class CarsListState(
    val cars: List<Car> = emptyList(),
    val carsFilter: List<Car> = emptyList(),
    val searchValue: String = "",
    val isLoading: Boolean = false,
    val error: UiText = emptyUiText
)