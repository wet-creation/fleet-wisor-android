package ua.com.fleetwisor.features.main_menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ua.com.fleetwisor.features.auth.presentation.auth.AuthAction
import ua.com.fleetwisor.features.auth.presentation.auth.AuthState

class MainMenuViewModel : ViewModel(){
    var state by mutableStateOf(MainMenuState())
        private set


    fun onAction(action: MainMenuAction) {

    }
}
