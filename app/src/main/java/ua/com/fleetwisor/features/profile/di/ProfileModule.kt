package ua.com.fleetwisor.features.profile.di

import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.features.profile.data.ProfileRepositoryImpl
import ua.com.fleetwisor.features.profile.domain.ProfileRepository
import ua.com.fleetwisor.features.profile.presentation.ProfileViewModel

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    singleOf(::ProfileRepositoryImpl).bind<ProfileRepository>()
}