package ua.com.fleetwisor.app.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.com.fleetwisor.app.MainViewModel

val mainModule = module {
    viewModelOf(::MainViewModel)
}

