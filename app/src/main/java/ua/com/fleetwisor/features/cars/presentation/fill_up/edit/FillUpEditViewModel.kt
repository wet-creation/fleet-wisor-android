package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FillUpEditViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(FillUpEditState())
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
            initialValue = FillUpEditState()
        )

    fun onAction(action: FillUpEditAction) {
        when (action) {
            is FillUpEditAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            else -> {}
        }
    }
}

