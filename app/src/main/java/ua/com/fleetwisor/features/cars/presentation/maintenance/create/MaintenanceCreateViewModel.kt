package ua.com.fleetwisor.features.cars.presentation.maintenance.create

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
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.MaintenanceRepository
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

class MaintenanceCreateViewModel(
    private val maintenanceRepository: MaintenanceRepository,
    private val carRepository: CarRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(MaintenanceCreateState())
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
            initialValue = MaintenanceCreateState()
        )

    fun onAction(action: MaintenanceCreateAction) {
        when (action) {
            MaintenanceCreateAction.DismissErrorDialog -> _state.update { it.copy(error = emptyUiText) }
            is MaintenanceCreateAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            is MaintenanceCreateAction.InputDescription -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(description = action.value)
                    )
                }
            }

            is MaintenanceCreateAction.InputPrice -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(price = action.value)
                    )
                }
            }

            is MaintenanceCreateAction.Save -> {
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

                    when (val res = maintenanceRepository.save(
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

            is MaintenanceCreateAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.photo
                    )
                }
            }

            is MaintenanceCreateAction.SelectTimeDate -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(time = action.time)
                    )
                }
            }

            is MaintenanceCreateAction.SelectedCarIndex -> {
                _state.update {
                    it.copy(
                        maintenance = it.maintenance.copy(car = _state.value.cars[action.car])
                    )
                }
            }

            else -> {}

        }
    }

    private fun init() {
        viewModelScope.launch {
            when (val res = carRepository.getAll()) {
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
                            cars = res.data,
                            maintenance = Maintenance(car = res.data[0])
                        )
                    }
                }
            }
        }
    }

}