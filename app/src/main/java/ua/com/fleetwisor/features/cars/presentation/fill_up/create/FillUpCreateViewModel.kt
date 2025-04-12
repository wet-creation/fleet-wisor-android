package ua.com.fleetwisor.features.cars.presentation.fill_up.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FillUpCreateViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(FillUpCreateState())
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
            initialValue = FillUpCreateState()
        )

    fun onAction(action: FillUpCreateAction) {
        when (action) {
            is FillUpCreateAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            else -> {}
        }
    }

}