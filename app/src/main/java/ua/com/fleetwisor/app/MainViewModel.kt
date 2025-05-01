package ua.com.fleetwisor.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.asUiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.domain.AuthRepository

class MainViewModel(
    private val authRepository: AuthRepository,
) : ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(
                isCheckingAuth = true
            )
            updateSession()
            state = state.copy(
                isCheckingAuth = false
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            authRepository.authInfo().collect {
                state = state.copy(
                    isLoggedIn = it.refreshToken != ""
                )
            }

        }
    }

    private suspend fun updateSession() {
        when (val result = authRepository.refreshToken()) {
            is FullResult.Error -> {
                state = if (result.error == DataError.Network.UNAUTHORIZED) {
                    state.copy(
                        error = UiText.StringResource(R.string.error_update_token),
                        isCheckingAuth = false
                    )
                } else {
                    state.copy(
                        error = result.error.asUiText(),
                        isCheckingAuth = false
                    )
                }
            }

            is FullResult.Success -> {
                state = state.copy(
                    isCheckingAuth = false
                )

            }


        }


    }

    fun dismissDialog() {
        state = state.copy(error = emptyUiText)
    }
}
