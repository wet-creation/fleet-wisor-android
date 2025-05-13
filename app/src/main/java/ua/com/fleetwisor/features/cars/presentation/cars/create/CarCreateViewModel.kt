package ua.com.fleetwisor.features.cars.presentation.cars.create

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
import ua.com.fleetwisor.core.domain.utils.millisToLocalDate
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.domain.models.Insurance
import ua.com.fleetwisor.features.drivers.domain.DriverRepository

class CarCreateViewModel(
    private val driverRepository: DriverRepository,
    private val carRepository: CarRepository,
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(CarCreateState())
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
            initialValue = CarCreateState()
        )

    fun onAction(action: CarCreateAction) {
        when (action) {
            CarCreateAction.DismissErrorDialog -> {
                _state.update { it.copy(error = emptyUiText) }
            }

            is CarCreateAction.ChangeTabIndex -> {
                _state.update { it.copy(selectedTab = action.index) }
            }

            is CarCreateAction.DeleteFuelType -> {
                val mutableList = state.value.car.fuelTypes.toMutableList()
                mutableList.removeAt(action.fuelTypeIndex)

                _state.update {
                    it.copy(
                        car = it.car.copy(
                            fuelTypes = mutableList.toSet()
                        )
                    )
                }
            }

            is CarCreateAction.InputBrandName -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            brandName = action.value
                        )
                    )
                }
            }

            is CarCreateAction.InputColor -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            color = action.value
                        )
                    )
                }
            }

            is CarCreateAction.InputLicensePlate -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            licensePlate = action.value
                        )
                    )
                }
            }

            is CarCreateAction.InputModel -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            model = action.value
                        )
                    )
                }
            }

            is CarCreateAction.InputOdometer -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            mileAge = action.value.toLongOrNull() ?: 0
                        )
                    )
                }
            }

            is CarCreateAction.InputVin -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            vin = action.value
                        )
                    )
                }
            }

            is CarCreateAction.SearchDriver -> {
                _state.update {
                    it.copy(
                        driverSearchValue = action.value,
                        driversFilter = state.value.drivers.filter {
                            it.fullName.contains(
                                action.value,
                                true
                            )
                        }
                    )
                }
            }

            is CarCreateAction.SelectCarBody -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            carBody = _state.value.carBodies.find { it.id == action.carBodyId }
                                ?: CarBody()
                        )
                    )
                }
            }

            is CarCreateAction.SelectDriver -> {
                val allDrivers = state.value.drivers
                val driverList = action.drivers.map { index -> allDrivers[index] }
                _state.update {
                    it.copy(
                        selectedDrivers = action.drivers,
                        car = it.car.copy(
                            drivers = driverList
                        )
                    )
                }
            }

            is CarCreateAction.SelectFuelType -> {
                val mutableList = state.value.car.fuelTypes.toMutableList()
                val fuelType =
                    _state.value.fuelTypes.find { it.id == action.fuelTypeId } ?: FuelType()
                mutableList[action.index] = fuelType
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            fuelTypes = mutableList.toSet()
                        )
                    )
                }

            }

            is CarCreateAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.uri
                    )
                }
            }

            is CarCreateAction.SelectPeriod -> {
                _state.update {
                    it.copy(
                        insurance = it.insurance.copy(
                            startDate = action.start.millisToLocalDate(),
                            endDate = action.end.millisToLocalDate()
                        )
                    )
                }
            }

            CarCreateAction.AddFuelType -> {
                if (state.value.car.fuelTypes.size >= state.value.fuelTypes.size)
                    return
                val mutableList = state.value.car.fuelTypes.toMutableList()
                mutableList.add(FuelType())
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            fuelTypes = mutableList.toSet()
                        )
                    )
                }
            }

            is CarCreateAction.SaveCar -> {
                val contentResolver = action.context.contentResolver
                var photo: Deferred<Pair<String, ByteArray>>? = null
                var insurance: Insurance? =
                    if (state.value.insurance == Insurance()) null else state.value.insurance
                viewModelScope.launch {
                    if (state.value.selectedPhoto != null)
                        photo = async(Dispatchers.IO) {
                            Pair(
                                contentResolver.getType(state.value.selectedPhoto!!)!!,
                                contentResolver.openInputStream(state.value.selectedPhoto!!)
                                    ?.toByteArray()!!
                            )
                        }
                    when (val res =
                        carRepository.saveCar(state.value.car, insurance, photo?.await())) {
                        is FullResult.Error -> {
                            _state.update { it.copy(error = res.asErrorUiText()) }

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
        viewModelScope.launch(Dispatchers.Default) {
            when (val res = driverRepository.getList()) {
                is FullResult.Error -> {
                    _state.update { it.copy(error = res.asErrorUiText()) }
                }

                is FullResult.Success -> {
                    _state.update { it.copy(drivers = res.data, driversFilter = res.data) }
                }
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            when (val res = carRepository.getAllCarBody()) {
                is FullResult.Error -> {
                    _state.update { it.copy(error = res.asErrorUiText()) }
                }

                is FullResult.Success -> {
                    _state.update {
                        it.copy(
                            carBodies = res.data
                        )
                    }
                }
            }
        }
        viewModelScope.launch(Dispatchers.Default) {
            when (val res = carRepository.getAllFuelType()) {
                is FullResult.Error -> {
                    _state.update { it.copy(error = res.asErrorUiText()) }
                }

                is FullResult.Success -> {
                    _state.update {
                        it.copy(
                            fuelTypes = res.data
                        )
                    }
                }
            }
        }

    }

}