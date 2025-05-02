package ua.com.fleetwisor.features.cars.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.cars.data.CarRepositoryImpl
import ua.com.fleetwisor.features.cars.data.FillUpRepositoryImpl
import ua.com.fleetwisor.features.cars.data.MaintenanceRepositoryImpl
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.domain.FillUpRepository
import ua.com.fleetwisor.features.cars.domain.MaintenanceRepository
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateViewModel
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditViewModel
import ua.com.fleetwisor.features.cars.presentation.cars.list.CarsListViewModel
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateViewModel
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditViewModel
import ua.com.fleetwisor.features.cars.presentation.fill_up.list.FillUpListViewModel
import ua.com.fleetwisor.features.cars.presentation.maintenance.list.MaintenanceListViewModel

val carModule = module {
    singleOf(::CarRepositoryImpl).bind<CarRepository>()
    singleOf(::FillUpRepositoryImpl).bind<FillUpRepository>()
    singleOf(::MaintenanceRepositoryImpl).bind<MaintenanceRepository>()
    viewModelOf(::CarsListViewModel)
    viewModelOf(::FillUpListViewModel)
    viewModelOf(::MaintenanceListViewModel)
    viewModelOf(::CarCreateViewModel)
    viewModelOf(::CarEditViewModel)
    viewModelOf(::FillUpCreateViewModel)
    viewModelOf(::FillUpEditViewModel)
}