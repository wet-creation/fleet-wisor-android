package ua.com.fleetwisor.features.auth.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.com.fleetwisor.features.auth.presentation.auth.AuthViewModel
import ua.com.fleetwisor.features.auth.presentation.login.LoginViewModel
import ua.com.fleetwisor.features.auth.presentation.register.RegisterViewModel

val authModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}