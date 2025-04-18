package ua.com.fleetwisor.features.main_menu.presentation

import ua.com.fleetwisor.core.domain.utils.firstDayOfMonth
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport
import java.time.LocalDate

data class MainMenuState(
    val reports: List<CarReport> = emptyList(),
    val selectedReport: CarReport = CarReport(),
    val startDate: LocalDate = LocalDate.now().firstDayOfMonth(),
    val endDate: LocalDate = LocalDate.now(),
    val error: UiText = emptyUiText,
)
