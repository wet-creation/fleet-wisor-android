package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.cars.domain.MaintenanceRepository

class MaintenanceEditViewModel(
    private val id: Int,
    private val maintenanceRepository: MaintenanceRepository,
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(MaintenanceEditState())
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
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
            initialValue = MaintenanceEditState()
        )

    fun onAction(action: MaintenanceEditAction) {
        when (action) {
            is MaintenanceEditAction.ChangeTabIndex -> {
                _state.update { it.copy(selectedTab = action.index) }
            }

            MaintenanceEditAction.Delete -> {
                viewModelScope.launch {
                    when (val res = maintenanceRepository.delete(id)) {
                        is FullResult.Error -> {
                            _state.update {
                                it.copy(
                                    error = res.asErrorUiText()
                                )
                            }
                        }

                        is FullResult.Success -> {
                            eventChannel.send(true)

                        }
                    }
                }
            }

            is MaintenanceEditAction.InputDescription -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(description = action.value)
                    )
                }
            }

            is MaintenanceEditAction.InputPrice -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(price = action.value)
                    )
                }
            }

            is MaintenanceEditAction.Save -> {
                val contentResolver = action.context.contentResolver
                var photo: Deferred<Pair<String, ByteArray>?>? = null
                viewModelScope.launch {
                    if (state.value.selectedPhoto != null) {
                        photo = async(Dispatchers.IO) {
                            Pair(
                                contentResolver.getType(state.value.selectedPhoto!!)!!,
                                contentResolver.openInputStream(state.value.selectedPhoto!!)
                                    ?.toByteArray()!!
                            )
                        }
                    }

                    when (val res = maintenanceRepository.edit(
                        maintenance = state.value.maintenance,
                        photo?.await(),
                    )) {
                        is FullResult.Error -> {
                            _state.update {
                                it.copy(
                                    error = res.asErrorUiText()
                                )
                            }
                        }

                        is FullResult.Success -> {
                            eventChannel.send(true)
                        }
                    }
                }
            }

            is MaintenanceEditAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.photo
                    )
                }
            }

            is MaintenanceEditAction.SelectTimeDate -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(time = action.time)
                    )
                }
            }


            else -> {}


        }
    }

    private fun init() {

        viewModelScope.launch {
            when (val res = maintenanceRepository.get(id)) {
                is FullResult.Error -> {
                    _state.update {
                        it.copy(
                            error = res.asErrorUiText()
                        )
                    }
                }

                is FullResult.Success -> {
                    _state.update {
                        it.copy(
                            maintenance = res.data
                        )
                    }
                }
            }
        }
    }
}

