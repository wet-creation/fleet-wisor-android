package ua.com.fleetwisor.features.main_menu.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.auth.data.AuthRepositoryImpl
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.main_menu.data.MainMenuRepositoryImpl
import ua.com.fleetwisor.features.main_menu.domain.MainMenuRepository
import ua.com.fleetwisor.features.main_menu.presentation.MainMenuViewModel

val mainMenuModule = module {
    singleOf(::MainMenuRepositoryImpl).bind<MainMenuRepository>()
    viewModelOf(::MainMenuViewModel)

}