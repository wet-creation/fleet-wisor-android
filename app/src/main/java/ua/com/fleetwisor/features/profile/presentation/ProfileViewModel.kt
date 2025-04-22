package ua.com.fleetwisor.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.data.local.LocalAuthService
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.validators.UserDataValidator
import ua.com.fleetwisor.core.presentation.ui.utils.UiText.*
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.profile.domain.ProfileRepository

class ProfileViewModel(
    private val localAuthService: LocalAuthService,
    private val repository: ProfileRepository,
    private val passwordValidator: UserDataValidator
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(ProfileState())
    val state = _state.onStart {
        if (!hasLoadedInitialData) {
            init()
            hasLoadedInitialData = true
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = ProfileState()
    )

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val res = repository.getUser()) {
                is FullResult.Error -> {}
                is FullResult.Success -> {
                    _state.update { it.copy(newOwner = res.data, owner = res.data) }
                }
            }
        }
    }

    fun onAction(action: ProfileAction) {
        when (action) {
            ProfileAction.OnLogOut -> viewModelScope.launch(Dispatchers.IO) {
                localAuthService.logout()
            }

            ProfileAction.ChangeInfo -> {

                viewModelScope.launch(Dispatchers.IO) {
                    _state.update { it.copy(savingInProgress = true) }

                    when (val res = repository.changeInfo(state.value.newOwner)) {
                        is FullResult.Error -> {
                            _state.update { it.copy(error = res.asErrorUiText()) }
                        }

                        is FullResult.Success -> {
                            _state.update { it.copy(owner = res.data, newOwner = res.data) }
                        }
                    }
                    _state.update { it.copy(savingInProgress = false, saved = true) }
                    delay(900)
                    _state.update { it.copy(saved = false) }

                }
            }

            is ProfileAction.InputConfirmPassword -> {
                _state.update { it.copy(confirmPassword = action.value) }
            }

            is ProfileAction.InputEmail -> {
                _state.update { it.copy(newOwner = it.newOwner.copy(email = action.value)) }
            }

            is ProfileAction.InputName -> {
                _state.update { it.copy(newOwner = it.newOwner.copy(name = action.value)) }
            }

            is ProfileAction.InputNewPassword -> {
                _state.update {
                    it.copy(
                        newPassword = action.value,
                        isPasswordValid = passwordValidator.validatePassword(action.value)
                    )
                }

            }

            is ProfileAction.InputOldPassword -> {
                _state.update { it.copy(oldPassword = action.value, passwordError = emptyUiText) }

            }

            is ProfileAction.InputSurname -> {
                _state.update { it.copy(newOwner = it.newOwner.copy(surname = action.value)) }
            }

            ProfileAction.SaveNewPassword -> {
                if (state.value.newPassword == state.value.confirmPassword)
                    viewModelScope.launch(
                        Dispatchers.IO
                    ) {
                        _state.update { it.copy(savingInProgress = true) }

                        when (val res = repository.changePassword(
                            state.value.oldPassword,
                            state.value.newPassword
                        )) {
                            is FullResult.Error -> {
                                if (res.error == DataError.Network.BAD_REQUEST) {
                                    _state.update { it.copy(passwordError = StringResource(R.string.old_password_incorrect)) }
                                } else _state.update { it.copy(error = res.asErrorUiText()) }
                            }

                            is FullResult.Success -> {
                                _state.update {
                                    it.copy(
                                        oldPassword = "",
                                        newPassword = "",
                                        confirmPassword = ""
                                    )
                                }

                            }
                        }
                        _state.update { it.copy(savingInProgress = false, saved = true) }
                        delay(900)
                        _state.update { it.copy(saved = false) }
                    }
            }

            ProfileAction.TogglePasswordVisibility -> {
                _state.update { it.copy(isPasswordVisible = !state.value.isPasswordVisible) }
            }

            ProfileAction.OnErrorCloseClick -> {
                _state.update { it.copy(error = emptyUiText) }

            }
        }
    }

}