package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ua.com.fleetwisor.features.cars.presentation.maintenance.create.MaintenanceCreateAction

class MaintenanceEditViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MaintenanceEditState())
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
            initialValue = MaintenanceEditState()
        )

    fun onAction(action: MaintenanceEditAction) {
        when (action) {
            is MaintenanceEditAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            else -> {}
        }
    }

}