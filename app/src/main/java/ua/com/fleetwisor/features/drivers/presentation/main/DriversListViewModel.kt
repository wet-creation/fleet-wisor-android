package ua.com.fleetwisor.features.drivers.presentation.main

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
import ua.com.fleetwisor.features.drivers.domain.DriverRepository

class DriversListViewModel(
    private val repository: DriverRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(DriversListState())
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
            initialValue = DriversListState()
        )

    fun onAction(action: DriversListAction) {
        when (action) {
            DriversListAction.Refresh -> {
                init()
            }

            is DriversListAction.SearchDriver -> {
                _state.update {
                    it.copy(
                        searchValue = action.value,
                        driversFilter = it.drivers.filter { it.fullName.contains(action.value, true) }
                    )
                }
            }

            else -> {}
        }
    }

    private fun init() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            when (val res = repository.getList()) {
                is FullResult.Error -> _state.update {
                    it.copy(
                        error = res.asErrorUiText()
                    )
                }

                is FullResult.Success -> _state.update {
                    it.copy(
                        drivers = res.data,
                        driversFilter = res.data
                    )
                }
            }
            _state.update { it.copy(isLoading = false) }
        }

    }

}