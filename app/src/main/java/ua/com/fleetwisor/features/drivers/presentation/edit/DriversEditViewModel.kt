package ua.com.fleetwisor.features.drivers.presentation.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.domain.utils.isDouble
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.drivers.domain.DriverRepository

class DriversEditViewModel(
    private val repository: DriverRepository,
    private val driverId: Int
) : ViewModel() {


    private val eventChannel = Channel<DriverEditEvents>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(DriversEditState())
    val state: StateFlow<DriversEditState> = _state

    init {
        init()

    }

    fun onAction(action: DriversEditAction) {
        when (action) {
            DriversEditAction.ConfirmDeleteDriver -> {
                _state.update { it.copy(isSaving = true) }
                viewModelScope.launch(Dispatchers.IO) {
                    when (val res = repository.deleteDriver(state.value.driver.id)) {
                        is FullResult.Error -> {
                            _state.update {
                                it.copy(error = res.asErrorUiText())
                            }
                        }

                        is FullResult.Success -> {
                            eventChannel.send(DriverEditEvents.DeletionComplete)
                        }
                    }
                }
            }

            DriversEditAction.DismissDialog -> {
                _state.update { it.copy(error = emptyUiText) }
            }

            DriversEditAction.DismissErrorDialog -> {
                _state.update {
                    it.copy(error = emptyUiText)
                }
            }

            is DriversEditAction.InputLicenseNumber -> {
                _state.update { it.copy(editDriver = it.editDriver.copy(driverLicenseNumber = action.value)) }
            }

            is DriversEditAction.InputName -> {
                _state.update { it.copy(editDriver = it.editDriver.copy(name = action.value)) }

            }

            is DriversEditAction.InputPhoneNumber -> {
                _state.update { it.copy(editDriver = it.editDriver.copy(phoneNumber = action.value)) }

            }

            is DriversEditAction.InputSalary -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            inputSalary = action.value,
                            editDriver = it.editDriver.copy(
                                salary = action.value.toDoubleOrNull() ?: 0.0
                            )
                        )
                    }

            }

            is DriversEditAction.InputSurname -> {
                _state.update { it.copy(editDriver = it.editDriver.copy(surname = action.value)) }

            }

            is DriversEditAction.SaveDriver -> {
                _state.update {
                    it.copy(
                        isSaving = true
                    )
                }
                val contentResolver = action.context.contentResolver
                var frontPhoto: Pair<String, ByteArray>? = null
                var backPhoto: Pair<String, ByteArray>? = null
                if (state.value.selectedFrontPhoto != null) {
                    frontPhoto =
                        Pair(
                            contentResolver.getType(state.value.selectedFrontPhoto!!)!!,
                            contentResolver.openInputStream(state.value.selectedFrontPhoto!!)
                                ?.toByteArray()!!
                        )
                }
                if (state.value.selectedBackPhoto != null)
                    backPhoto =
                        Pair(
                            contentResolver.getType(state.value.selectedBackPhoto!!)!!,
                            contentResolver.openInputStream(state.value.selectedBackPhoto!!)
                                ?.toByteArray()!!
                        )

                viewModelScope.launch {
                    when (val res =
                        repository.editDriver(state.value.editDriver, frontPhoto, backPhoto)) {
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
                                    editDriver = res.data,
                                    driver = res.data
                                )
                            }
                        }
                    }
                    _state.update {
                        it.copy(
                            isSaving = false
                        )
                    }
                }
            }

            is DriversEditAction.SelectBackPhoto -> {
                _state.update { it.copy(selectedBackPhoto = action.photo) }

            }

            is DriversEditAction.SelectBirthDay -> {
                _state.update { it.copy(editDriver = it.editDriver.copy(birthdayDate = action.value)) }

            }

            is DriversEditAction.SelectFrontPhoto -> {
                _state.update { it.copy(selectedFrontPhoto = action.photo) }

            }

            else -> {}
        }
    }


    private fun init() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val res = repository.getDriver(driverId)) {
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
                            driver = res.data,
                            editDriver = res.data
                        )
                    }
                }
            }
            _state.update { it.copy(isLoading = false) }
        }


    }

}