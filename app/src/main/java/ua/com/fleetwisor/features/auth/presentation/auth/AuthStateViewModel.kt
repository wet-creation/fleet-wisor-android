package ua.com.fleetwisor.features.auth.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthStateViewModel: ViewModel() {

    var state by mutableStateOf(AuthState())
        private set


    fun onAction(action: AuthAction) {

    }

}
