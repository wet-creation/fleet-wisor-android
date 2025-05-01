package ua.com.fleetwisor.features.cars.presentation.maintenance.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MaintenanceCreateViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MaintenanceCreateState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MaintenanceCreateState()
        )

    fun onAction(action: MaintenanceCreateAction) {
        when (action) {
            is MaintenanceCreateAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            else -> {}
        }
    }

}