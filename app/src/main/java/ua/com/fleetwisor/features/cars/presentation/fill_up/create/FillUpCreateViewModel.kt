package ua.com.fleetwisor.features.cars.presentation.fill_up.create

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
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.FillUpRepository
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.profile.domain.ProfileRepository
import ua.com.fleetwisor.features.profile.domain.models.FuelUnits

class FillUpCreateViewModel(
    private val carRepository: CarRepository,
    private val fillUpRepository: FillUpRepository,
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(FillUpCreateState())
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
            initialValue = FillUpCreateState()
        )

    fun onAction(action: FillUpCreateAction) {
        when (action) {
            is FillUpCreateAction.ChangeTabIndex -> _state.update { it.copy(selectedTab = action.index) }
             FillUpCreateAction.DismissErrorDialog -> _state.update { it.copy(error = emptyUiText) }
            is FillUpCreateAction.InputAmount -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            fillUp = it.fillUp.copy(
                                amount = action.value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }
            }

            is FillUpCreateAction.InputPrice -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            fillUp = it.fillUp.copy(
                                price = action.value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }
            }

            is FillUpCreateAction.SelectFuelType -> {
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


            is FillUpCreateAction.SelectedCarIndex -> {
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
            }

            is FillUpCreateAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.uri
                    )
                }
            }

            is FillUpCreateAction.SelectTimeDate -> {
                _state.update { fillUp ->
                    fillUp.copy(
                        fillUp = fillUp.fillUp.copy(
                            time = action.dateTime
                        )
                    )
                }

            }

            is FillUpCreateAction.Save -> {
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

                    when (val res = fillUpRepository.save(
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
            val carsRes = async { carRepository.getAll() }
            val fuelsRes = async { carRepository.getAllFuelType() }
            val userSettingsRes = async { profileRepository.getUserSettings() }

            val cars = carsRes.await()
            val fuels = fuelsRes.await()
            val userSettings = userSettingsRes.await()

            if (cars is FullResult.Error) {
                _state.update {
                    it.copy(
                        error = cars.asErrorUiText()
                    )
                }
                return@launch
            }

            if (fuels is FullResult.Error) {
                _state.update {
                    it.copy(
                        error = fuels.asErrorUiText()
                    )
                }
                return@launch

            }
            if (userSettings is FullResult.Error) {
                _state.update {
                    it.copy(
                        error = userSettings.asErrorUiText()
                    )
                }
                return@launch

            }
//todo check if cars is not empty
            val carsData = (cars as FullResult.Success).data
            val fuelsData = (fuels as FullResult.Success).data
            val userSettingsData = (userSettings as FullResult.Success).data.fuelUnits


            val selectedFuelType =
                fuelsData.find { carsData[0].fuelTypes.first().id == it.id }
            var unit = FuelUnits()
            selectedFuelType?.units?.forEach { units ->
                if (userSettingsData.map { it.id }.contains(units.id)) {
                    unit = units
                }
            }
            _state.update { fillUp ->
                fillUp.copy(
                    cars = carsData,
                    fuelTypes = fuelsData.toSet(),
                    userUnits = userSettingsData,
                    fillUp = fillUp.fillUp.copy(
                        car = carsData[0],
                        fuelType = selectedFuelType ?: FuelType(),
                        fuelUnits = unit

                    )
                )
            }
        }


    }

}