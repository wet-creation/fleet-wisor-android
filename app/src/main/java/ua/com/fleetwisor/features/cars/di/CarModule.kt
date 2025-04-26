package ua.com.fleetwisor.features.cars.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.cars.data.CarRepositoryImpl
import ua.com.fleetwisor.features.cars.domain.CarRepository
import ua.com.fleetwisor.features.cars.presentation.cars.list.CarsListViewModel

val carModule = module {
    singleOf(::CarRepositoryImpl).bind<CarRepository>()
    viewModelOf(::CarsListViewModel)
}