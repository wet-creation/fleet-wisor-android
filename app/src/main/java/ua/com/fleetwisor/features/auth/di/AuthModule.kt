package ua.com.fleetwisor.features.auth.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.auth.data.AuthRepositoryImpl
import ua.com.fleetwisor.features.auth.domain.AuthRepository
import ua.com.fleetwisor.features.auth.presentation.auth.AuthViewModel
import ua.com.fleetwisor.features.auth.presentation.login.LoginViewModel
import ua.com.fleetwisor.features.auth.presentation.register.RegisterViewModel

val authPresentationModule = module {
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    viewModelOf(::AuthViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}