package ua.com.fleetwisor.features.drivers.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.drivers.data.DriverRepositoryImpl
import ua.com.fleetwisor.features.drivers.domain.DriverRepository
import ua.com.fleetwisor.features.drivers.presentation.create.DriverCreateViewModel
import ua.com.fleetwisor.features.drivers.presentation.edit.DriversEditViewModel
import ua.com.fleetwisor.features.drivers.presentation.main.DriversListViewModel

val driverModule = module {
    singleOf(::DriverRepositoryImpl).bind<DriverRepository>()
    viewModelOf(::DriversListViewModel)
    viewModelOf(::DriverCreateViewModel)
    viewModel { (driverId: Int) ->
        DriversEditViewModel(get(), driverId)
    }
}