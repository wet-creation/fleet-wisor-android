package ua.com.fleetwisor.features.drivers.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.domain.utils.formatDate
import ua.com.fleetwisor.core.domain.utils.isDouble
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.toByteArray
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.drivers.domain.DriverRepository
import ua.com.fleetwisor.features.drivers.domain.models.CreateDriver

class DriverCreateViewModel(
    private val repository: DriverRepository
) : ViewModel() {

    private var hasLoadedInitialData = false
    private val eventChannel = Channel<Boolean>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(DriverCreateState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DriverCreateState()
        )

    fun onAction(action: DriverCreateAction) {
        when (action) {
            is DriverCreateAction.InputLicenseNumber -> {
                _state.update {
                    it.copy(inputLicenseNumber = action.value)
                }
            }

            is DriverCreateAction.InputName -> {
                _state.update {
                    it.copy(inputName = action.value)
                }

            }

            is DriverCreateAction.InputPhoneNumber -> {
                _state.update {
                    it.copy(inputPhone = action.value)
                }
            }

            is DriverCreateAction.InputSalary -> {
                if (action.value.isDouble())
                    _state.update {
                        it.copy(
                            inputSalary = action.value,
                            salary = action.value.toDoubleOrNull() ?: 0.0
                        )
                    }
            }

            is DriverCreateAction.InputSurname -> {
                _state.update {
                    it.copy(inputSurname = action.value)
                }
            }

            DriverCreateAction.OpenDateSelector -> {
                _state.update {
                    it.copy(isDateSelectorOpen = true)
                }
            }

            DriverCreateAction.DismissDateSelector -> {
                _state.update {
                    it.copy(isDateSelectorOpen = false)
                }
            }

            is DriverCreateAction.SaveDriver -> {
                val contentResolver = action.context.contentResolver
                var frontPhoto: Pair<String, ByteArray>? = null
                var backPhoto: Pair<String, ByteArray>? = null
                _state.update {
                    it.copy(savingInProgress = true)
                }
                val createDriver = CreateDriver(
                    name = state.value.inputName,
                    surname = state.value.inputSurname,
                    phoneNumber = state.value.inputPhone,
                    driverLicenseNumber = state.value.inputLicenseNumber,
                    birthdayDate = state.value.selectedBirthDay,
                    salary = state.value.salary
                )

                if (state.value.frontPhoto != null) {
                    frontPhoto =
                        Pair(contentResolver.getType(state.value.frontPhoto!!)!!,
                            contentResolver.openInputStream(state.value.frontPhoto!!)
                                ?.toByteArray()!!
                        )
                }
                if (state.value.backPhoto != null)
                    backPhoto =
                        Pair(contentResolver.getType(state.value.backPhoto!!)!!,
                            contentResolver.openInputStream(state.value.backPhoto!!)
                                ?.toByteArray()!!
                        )

                viewModelScope.launch(Dispatchers.IO) {
                    when (val res = repository.createDriver(createDriver, frontPhoto, backPhoto)) {
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
                    _state.update {
                        it.copy(savingInProgress = false)
                    }
                }
            }

            is DriverCreateAction.SelectBackPhoto -> {
                _state.update {
                    it.copy(
                        backPhoto = action.photo
                    )
                }
            }

            is DriverCreateAction.SelectFrontPhoto -> {
                _state.update {
                    it.copy(
                        frontPhoto = action.photo
                    )
                }
            }

            is DriverCreateAction.SelectBirthDay -> {
                _state.update {
                    it.copy(
                        isDateSelectorOpen = false,
                        selectedBirthDay = action.value,
                        inputBirthDay = action.value.formatDate()
                    )
                }
            }

            else -> {

            }
        }
    }

}