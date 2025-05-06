package ua.com.fleetwisor.features.cars.presentation.fill_up.edit

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
import ua.com.fleetwisor.core.domain.utils.isDouble
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.FillUpRepository
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.profile.domain.ProfileRepository
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

class FillUpEditViewModel(
    private val fillUpId: Int,
    private val carRepository: CarRepository,
    private val fillUpRepository: FillUpRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(FillUpEditState())
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
            initialValue = FillUpEditState()
        )

    fun onAction(action: FillUpEditAction) {
        when (action) {
            is FillUpEditAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
            is FillUpEditAction.InputAmount -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            fillUp = it.fillUp.copy(
                                amount = action.value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }
            }

            is FillUpEditAction.InputPrice -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            fillUp = it.fillUp.copy(
                                price = action.value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }
            }

            is FillUpEditAction.SelectFuelType -> {
                _state.update {
                    val selectedFuelType =
                        state.value.fuelTypes.find { it.id == action.id }
                    var unit = FuelUnits()
                    selectedFuelType?.units?.forEach { units ->
                        if (state.value.userUnits.map { it.id }.contains(units.id)) {
                            unit = units
                        }
                    }
                    it.copy(
                        fillUp = it.fillUp.copy(
                            fuelType = selectedFuelType ?: FuelType(),
                            fuelUnits = unit
                        )
                    )
                }
            }

            is FillUpEditAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.uri
                    )
                }
            }

            is FillUpEditAction.SelectTimeDate -> {
                _state.update { fillUp ->
                    fillUp.copy(
                        fillUp = fillUp.fillUp.copy(
                            time = action.dateTime
                        )
                    )
                }
            }

            is FillUpEditAction.SelectedCarIndex ->
                _state.update { fillUp ->

                    val selectedFuelType =
                        fillUp.fuelTypes.find { fillUp.cars[action.car].fuelTypes.first().id == it.id }

                    var unit = FuelUnits()
                    selectedFuelType?.units?.forEach { units ->
                        if (state.value.userUnits.map { it.id }.contains(units.id)) {
                            unit = units
                        }
                    }
                    fillUp.copy(
                        fillUp = fillUp.fillUp.copy(
                            fuelType = selectedFuelType ?: FuelType(),
                            fuelUnits = unit,
                            car = fillUp.cars[action.car]

                        )
                    )

                }

            is FillUpEditAction.Save -> {
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

                    when (val res = fillUpRepository.edit(
                        fillUp = state.value.fillUp,
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
                        }
                    }
                }


            }

            is FillUpEditAction.Delete -> {
                viewModelScope.launch {
                    when (val res = fillUpRepository.delete(fillUpId)) {
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

            else -> {}

        }
    }

    private fun init() {
        viewModelScope.launch {
            when (val res = fillUpRepository.get(fillUpId)) {
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
                            fillUp = res.data
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            when (val res = carRepository.getAllFuelType()) {
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
                            fuelTypes = res.data.toSet()
                        )
                    }
                }
            }
        }
        viewModelScope.launch {
            when (val res = profileRepository.getUserSettings()) {
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
                            userUnits = res.data.fuelUnits
                        )
                    }
                }
            }
        }
    }
}

