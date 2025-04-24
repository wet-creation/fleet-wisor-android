package ua.com.fleetwisor.features.drivers.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.auth.data.AuthRepositoryImpl
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.drivers.data.DriverRepositoryImpl
import ua.com.fleetwisor.features.drivers.domain.DriverRepository
import ua.com.fleetwisor.features.drivers.presentation.create.DriverCreateViewModel
import ua.com.fleetwisor.features.drivers.presentation.edit.DriversEditViewModel
import ua.com.fleetwisor.features.drivers.presentation.main.DriversListViewModel
import ua.com.fleetwisor.features.main_menu.data.MainMenuRepositoryImpl
import ua.com.fleetwisor.features.main_menu.domain.MainMenuRepository
import ua.com.fleetwisor.features.main_menu.presentation.MainMenuViewModel

val driverModule = module {
    singleOf(::DriverRepositoryImpl).bind<DriverRepository>()
    viewModelOf(::DriversListViewModel)
    viewModelOf(::DriverCreateViewModel)
    viewModelOf(::DriversEditViewModel)

}