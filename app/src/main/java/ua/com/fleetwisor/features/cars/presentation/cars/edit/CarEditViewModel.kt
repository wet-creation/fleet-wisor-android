package ua.com.fleetwisor.features.cars.presentation.cars.edit

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
import kotlinx.coroutines.withContext
import ua.com.fleetwisor.core.domain.utils.millisToLocalDate
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.drivers.domain.DriverRepository

class CarEditViewModel(
    private val driverRepository: DriverRepository,
    private val carRepository: CarRepository,
    private val carId: Int
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(CarEditState())
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
            initialValue = CarEditState()
        )

    fun onAction(action: CarEditAction) {
        when (action) {
            is CarEditAction.ChangeTabIndex -> {
                _state.update { it.copy(selectedTab = action.index) }
            }

            CarEditAction.DismissErrorDialog -> {
                _state.update { it.copy(error = emptyUiText) }
            }

            is CarEditAction.DeleteFuelType -> {
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

            is CarEditAction.InputBrandName -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            brandName = action.value
                        )
                    )
                }
            }

            is CarEditAction.InputColor -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            color = action.value
                        )
                    )
                }
            }

            is CarEditAction.InputLicensePlate -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            licensePlate = action.value
                        )
                    )
                }
            }

            is CarEditAction.InputModel -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            model = action.value
                        )
                    )
                }
            }

            is CarEditAction.InputOdometer -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            mileAge = action.value.toLongOrNull() ?: 0
                        )
                    )
                }
            }

            is CarEditAction.InputVin -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            vin = action.value
                        )
                    )
                }
            }

            is CarEditAction.SearchDriver -> {
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

            is CarEditAction.SelectCarBody -> {
                _state.update {
                    it.copy(
                        car = it.car.copy(
                            carBody = _state.value.carBodies.find { it.id == action.carBodyId }
                                ?: CarBody()
                        )
                    )
                }
            }

            is CarEditAction.SelectDriver -> {
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

            is CarEditAction.SelectFuelType -> {
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

            is CarEditAction.SelectPhoto -> {
                _state.update {
                    it.copy(
                        selectedPhoto = action.uri
                    )
                }
            }

            is CarEditAction.SelectPeriod -> {
                _state.update {
                    it.copy(
                        insurance = it.insurance.copy(
                            startDate = action.start.millisToLocalDate(),
                            endDate = action.end.millisToLocalDate()
                        )
                    )
                }
            }

            CarEditAction.AddFuelType -> {
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

            is CarEditAction.EditCar -> {
                val contentResolver = action.context.contentResolver

                viewModelScope.launch {
                    try {
                        val photoDeferred: Deferred<Pair<String, ByteArray>>? =
                            state.value.selectedPhoto?.let { uri ->
                                async(Dispatchers.IO) {
                                    val mime = contentResolver.getType(uri)
                                        ?: throw IllegalArgumentException("Mime type is null")
                                    val bytes = contentResolver.openInputStream(uri)
                                        ?.toByteArray()
                                        ?: throw IllegalArgumentException("Cannot read photo bytes")
                                    Pair(mime, bytes)
                                }
                            }

                        when (val carRes = withContext(Dispatchers.Default) {
                            carRepository.editCar(state.value.car)
                        }) {
                            is FullResult.Error -> {
                                _state.update { it.copy(error = carRes.asErrorUiText()) }
                                return@launch
                            }

                            is FullResult.Success -> {
                            }
                        }

                        photoDeferred?.let {
                            val photoData = it.await()
                            when (val insuranceRes = withContext(Dispatchers.Default) {
                                carRepository.editInsurance(
                                    state.value.car.id,
                                    state.value.insurance,
                                    photoData
                                )
                            }) {
                                is FullResult.Error -> {
                                    _state.update { it.copy(error = insuranceRes.asErrorUiText()) }
                                }

                                is FullResult.Success -> {

                                }
                            }
                        }

                    } catch (e: Exception) {
                        _state.update {
                            it.copy(
                                error = UiText.DynamicString(
                                    e.localizedMessage ?: "Unknown error"
                                )
                            )
                        }
                    }
                }

            }

            CarEditAction.DeleteCar -> {
                viewModelScope.launch {
                    when (val insuranceRes = withContext(Dispatchers.Default) {
                        carRepository.delete(state.value.car.id)
                    }) {
                        is FullResult.Error -> {
                            _state.update { it.copy(error = insuranceRes.asErrorUiText()) }
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
        viewModelScope.launch(Dispatchers.Default) {
            try {
                val carDeferred = async { carRepository.getCar(carId) }
                val driversDeferred = async { driverRepository.getList() }

                val carRes = carDeferred.await()
                val driversRes = driversDeferred.await()

                if (carRes is FullResult.Error) {
                    _state.update { it.copy(error = carRes.asErrorUiText()) }
                    return@launch
                }

                if (driversRes is FullResult.Error) {
                    _state.update { it.copy(error = driversRes.asErrorUiText()) }
                    return@launch
                }

                val car = (carRes as FullResult.Success).data
                val drivers = (driversRes as FullResult.Success).data

                _state.update {
                    it.copy(
                        car = car,
                        carOrigin = car,
                        drivers = drivers,
                        driversFilter = drivers,
                        selectedDrivers = car.drivers.map { driver -> drivers.indexOf(driver) }
                    )
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        error = UiText.DynamicString(
                            e.localizedMessage ?: ""
                        )
                    )
                }
            }

        }
        viewModelScope.launch(Dispatchers.Default) {
            when (val res = carRepository.getInsurance(carId)) {
                is FullResult.Error -> {
                    _state.update { it.copy(error = res.asErrorUiText()) }
                }

                is FullResult.Success -> {
                    _state.update {
                        it.copy(
                            insurance = res.data,
                            originInsurance = res.data,
                        )
                    }
                }
            }
        }

    }

}