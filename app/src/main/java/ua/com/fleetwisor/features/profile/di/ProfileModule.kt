package ua.com.fleetwisor.features.profile.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ua.com.fleetwisor.features.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModelOf(::ProfileViewModel)
}