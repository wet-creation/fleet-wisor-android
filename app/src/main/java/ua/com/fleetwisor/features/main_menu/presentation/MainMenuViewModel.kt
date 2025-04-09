package ua.com.fleetwisor.features.main_menu.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainMenuViewModel : ViewModel(){
    var state by mutableStateOf(MainMenuState())
        private set


    fun onAction(action: MainMenuAction) {

    }
}
