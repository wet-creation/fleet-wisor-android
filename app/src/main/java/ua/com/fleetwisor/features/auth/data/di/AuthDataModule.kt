package ua.com.fleetwisor.features.auth.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.auth.data.AuthRepositoryImpl
import ua.com.fleetwisor.features.auth.domain.AuthRepository

val authDataModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}