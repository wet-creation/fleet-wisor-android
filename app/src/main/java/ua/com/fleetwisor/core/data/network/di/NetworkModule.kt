package ua.com.fleetwisor.core.data.network.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.services.auth.RemoteAuthService
import ua.com.fleetwisor.core.data.network.services.auth.RemoteAuthServiceImpl
import ua.com.fleetwisor.core.data.network.services.car.fillup.FillUpService
import ua.com.fleetwisor.core.data.network.services.car.fillup.FillUpServiceImpl
import ua.com.fleetwisor.core.data.network.services.car.main.CarService
import ua.com.fleetwisor.core.data.network.services.car.main.CarServiceImpl
import ua.com.fleetwisor.core.data.network.services.car.maintenance.MaintenanceService
import ua.com.fleetwisor.core.data.network.services.car.maintenance.MaintenanceServiceImpl
import ua.com.fleetwisor.core.data.network.services.common.FuelTypeNCarBodyService
import ua.com.fleetwisor.core.data.network.services.common.FuelTypeNCarBodyServiceImpl
import ua.com.fleetwisor.core.data.network.services.driver.DriverService
import ua.com.fleetwisor.core.data.network.services.driver.DriverServiceImpl
import ua.com.fleetwisor.core.data.network.services.main_menu.MainMenuSource
import ua.com.fleetwisor.core.data.network.services.main_menu.MainMenuSourceImpl
import ua.com.fleetwisor.core.data.network.services.profile.ProfileService
import ua.com.fleetwisor.core.data.network.services.profile.ProfileServiceImpl

val networkModule = module {
    single { HttpClientFactory(get()) }

    singleOf(::RemoteAuthServiceImpl).bind<RemoteAuthService>()
    singleOf(::MainMenuSourceImpl).bind<MainMenuSource>()
    singleOf(::ProfileServiceImpl).bind<ProfileService>()
    singleOf(::DriverServiceImpl).bind<DriverService>()
    singleOf(::CarServiceImpl).bind<CarService>()
    singleOf(::FillUpServiceImpl).bind<FillUpService>()
    singleOf(::MaintenanceServiceImpl).bind<MaintenanceService>()
    singleOf(::FuelTypeNCarBodyServiceImpl).bind<FuelTypeNCarBodyService>()

}