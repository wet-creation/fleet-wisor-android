package ua.com.fleetwisor.features.main_menu.presentation

import ua.com.fleetwisor.core.domain.utils.firstDayOfMonth
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport
import java.time.LocalDate

data class MainMenuState(
    val reports: List<CarReport> = emptyList(),
    val filteredReports: List<CarReport> = emptyList(),
    val selectedReport: CarReport = CarReport(),
    val startDate: LocalDate = LocalDate.now().firstDayOfMonth(),
    val endDate: LocalDate = LocalDate.now(),
    val error: UiText = emptyUiText,
    val searchCarValue: String = "",
    val isLoading: Boolean = false,
    val downloadState: DownloadState = DownloadState.Idle,
    val modalBottomSheetState: ModalBottomSheetState = ModalBottomSheetState()
)

data class ModalBottomSheetState(
    val isOpen: Boolean = false,
    val showReportsList: Boolean = false
)

sealed interface DownloadState {
    data class Error(val error: UiText): DownloadState
    data object Success: DownloadState
    data object Idle: DownloadState
    data object Downloading: DownloadState
}