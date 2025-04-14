package ua.com.fleetwisor.features.auth.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.validators.UserDataValidator
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.auth.domain.models.RegisterInfo
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState
import kotlin.compareTo

class RegisterViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterScreenState())
        private set


    fun onAction(action: RegisterAction) {
        when (action) {
            is RegisterAction.OnEmailChange -> {
                if (state.registerState == RegisterState.CONFLICT_EMAIL) state =
                    state.copy(registerState = RegisterState.NONE)
                state = state.copy(
                    email = action.email,
                    isEmailValid = userDataValidator.isValidEmail(action.email)
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            RegisterAction.OnErrorRegistration -> {
                state = state.copy(
                    error = emptyUiText,
                    registerState = RegisterState.NONE,
                    canRegister = false
                )
            }

            is RegisterAction.OnNameChange -> {
                state = state.copy(
                    name = action.name
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            is RegisterAction.OnPasswordChange -> {
                state = state.copy(
                    password = action.password,
                    isPasswordValid = userDataValidator.validatePassword(action.password)
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            is RegisterAction.OnPasswordCheckChange -> {
                state = state.copy(
                    passwordConformation = action.passwordCheck
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            RegisterAction.OnPasswordVisibilityClick -> {
                state = state.copy(
                    isPasswordVisible = !state.isPasswordVisible
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            RegisterAction.OnRegisterClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    state = state.copy(
                        isRegistering = true
                    )
                    val result = authRepository.register(
                        RegisterInfo(
                            name = state.name.trim(),
                            surname = state.name.trim(),
                            email = state.email.trim(),
                            password = state.password.trim()
                        )
                    )
                    state = state.copy(
                        isRegistering = false
                    )
                   when(result) {
                       is FullResult.Error-> {
                           if(result.error == DataError.Network.CONFLICT) {
                               state.copy(
                                   error = UiText.StringResource(R.string.error_email_exists),
                                   registerState = RegisterState.CONFLICT_EMAIL,
                                   canRegister = false
                               )
                           }
                           else {
                               state.copy(
                               error = result.asErrorUiText(),
                               registerState = RegisterState.ERROR,
                               canRegister = true
                               )
                           }
                       }
                       is FullResult.Success -> {
                           state = state.copy(
                               registerState = RegisterState.SUCCESS
                           )
                       }
                   }
                    state = state.copy(
                        isRegistering = true
                    )

                }
            }

            is RegisterAction.OnSurnameChange -> {
                state = state.copy(
                    surname = action.surname
                )
                state = state.copy(
                    canRegister = canRegister()
                )
            }

            else -> {

            }
        }
    }

    private fun canRegister(): Boolean {
        return state.name.length > 1 && state.surname.length > 1 && state.isEmailValid
                && state.isPasswordValid.isValidPassword && state.password == state.passwordConformation && !state.isRegistering
    }
}
