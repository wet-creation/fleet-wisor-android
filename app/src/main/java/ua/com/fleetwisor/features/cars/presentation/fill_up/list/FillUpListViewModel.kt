package ua.com.fleetwisor.features.cars.presentation.fill_up.list

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
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.FillUpRepository

class FillUpListViewModel(
    private val repository: FillUpRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(FilUpListState())
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
            initialValue = FilUpListState()
        )

    fun onAction(action: FilUpListAction) {
        when (action) {
            is FilUpListAction.InputSearch -> {
                _state.update {
                    it.copy(
                        filteredFillUps = it.fillUps.filter { it.car.name.contains(action.value, true) }
                    )
                }
            }
            FilUpListAction.Refresh -> {
                init()
            }
            FilUpListAction.DismissErrorDialog -> {
                _state.update {
                    it.copy(
                        error = emptyUiText
                    )
                }
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
                        fillUps = res.data,
                        filteredFillUps = res.data
                    )
                }

            }
            _state.update { it.copy(isLoading = false) }
        }
    }

}