package ua.com.fleetwisor.features.main_menu.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.com.fleetwisor.features.main_menu.presentation.MainMenuViewModel

val mainMenuModule = module {
    viewModelOf(::MainMenuViewModel)
}