package ua.com.fleetwisor.features.main_menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.main_menu.domain.MainMenuRepository
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport

class MainMenuViewModel(
    private val repository: MainMenuRepository
) : ViewModel() {
    var state by mutableStateOf(MainMenuState())
        private set


    init {
        getReports()
    }


    fun onAction(action: MainMenuAction) {

    }

    private fun getReports() {
        viewModelScope.launch(Dispatchers.IO) {
            state = when (val res = repository.getReports(state.startDate, state.endDate)) {
                is FullResult.Error -> {
                    state.copy(error = res.asErrorUiText())
                }

                is FullResult.Success -> {
                    val summary = res.data.fold(
                        CarReport(
                            brandName = "Усі авто",
                            fillUpCount = 0,
                            totalFillUp = 0.0,
                            maintenanceCount = 0,
                            totalMaintenance = 0.0
                        )
                    ) { acc, report ->
                        acc.copy(
                            fillUpCount = acc.fillUpCount + report.fillUpCount,
                            totalFillUp = acc.totalFillUp + report.totalFillUp,
                            maintenanceCount = acc.maintenanceCount + report.maintenanceCount,
                            totalMaintenance = acc.totalMaintenance + report.totalMaintenance
                        )
                    }
                    val reports = buildList {
                        add(summary)
                        addAll(res.data)
                    }
                    state.copy(reports = reports, selectedReport = summary)
                }
            }
        }
    }

}
