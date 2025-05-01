package ua.com.fleetwisor.features.cars.presentation.maintenance.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.cars.domain.MaintenanceRepository

class MaintenanceListViewModel(
    private val repository: MaintenanceRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MaintenanceListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                init()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MaintenanceListState()
        )

    fun onAction(action: MaintenanceListAction) {
        when (action) {
            is MaintenanceListAction.InputSearch -> {
                _state.update {
                    it.copy(
                        searchValue = action.value,
                        filteredMaintenances = it.maintenances.filter { it.car.name.contains(action.value, true) }
                    )
                }
            }
            MaintenanceListAction.Refresh -> {
                init()
            }
            else -> {}
        }
    }

    private fun init() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            when (val res = repository.getAll()) {
                is FullResult.Error -> _state.update {
                    it.copy(
                        error = res.asErrorUiText()
                    )
                }

                is FullResult.Success -> _state.update {
                    it.copy(
                        maintenances = res.data,
                        filteredMaintenances = res.data
                    )
                }

            }
            _state.update { it.copy(isLoading = false) }
        }
    }

}