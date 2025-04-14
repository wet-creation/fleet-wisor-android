package ua.com.fleetwisor.core.data.network.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.services.auth.RemoteAuthService
import ua.com.fleetwisor.core.data.network.services.auth.RemoteAuthServiceImpl

val networkModule = module {
    single { HttpClientFactory(get(), get()) }

    singleOf(::RemoteAuthServiceImpl).bind<RemoteAuthService>()

}