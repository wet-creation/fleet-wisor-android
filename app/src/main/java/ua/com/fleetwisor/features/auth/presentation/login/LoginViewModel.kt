package ua.com.fleetwisor.features.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.domain.utils.validators.UserDataValidator
import ua.com.fleetwisor.core.presentation.ui.utils.UiText.*
import ua.com.fleetwisor.core.presentation.ui.utils.asUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState

class LoginViewModel(
    private val userDataValidator: UserDataValidator,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val eventChannel = Channel<LoginEvent>()
    val events = eventChannel.receiveAsFlow()
    var state by mutableStateOf(LoginState())
        private set

    fun onAction(action: LoginAction) {

        when (action) {
            is LoginAction.InputEmail -> {
                state = state.copy(
                    email = action.email,
                    isEmailValid = userDataValidator.isValidEmail(action.email),
                    loginState = RegisterState.NONE
                )
                state = state.copy(
                    canLogin = state.password.length > 5 && state.isEmailValid,
                )
            }

            is LoginAction.InputPassword -> {
                state = state.copy(password = action.password)
                state = state.copy(
                    canLogin = state.password.length > 5 && state.isEmailValid,
                )
            }
            LoginAction.OnLogin -> {
                viewModelScope.launch(Dispatchers.IO) {
                    state = state.copy(isLoggingIn = true)
                    val result = authRepository.login(
                        email = state.email.trim(),
                        password = state.password
                    )
                    state = state.copy(isLoggingIn = false)

                    when (result) {
                        is FullResult.Error -> {
                            state = if (result.error == DataError.Network.UNAUTHORIZED) {
                                state.copy(
                                    error = StringResource(R.string.error_email_password_incorrect),
                                    loginState = RegisterState.CONFLICT
                                )
                            } else {
                                state.copy(
                                    error = result.error.asUiText(),
                                    loginState = RegisterState.ERROR
                                )
                            }
                        }

                        is FullResult.Success -> {
                            eventChannel.send(LoginEvent.LoginSuccess)
                        }
                    }
                }
            }

            LoginAction.TogglePasswordVisibility -> {
                state = state.copy(passwordVisible = !state.passwordVisible)
            }

            LoginAction.OnErrorCloseClick -> {
                state = state.copy(
                    error = emptyUiText,
                    loginState = RegisterState.NONE
                )
            }

        }

    }

}
