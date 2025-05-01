package ua.com.fleetwisor.core.data.local.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.core.data.local.auth.LocalAuthService
import ua.com.fleetwisor.core.data.local.auth.LocalAuthServiceImpl
import ua.com.fleetwisor.core.data.local.settings.LocalSettingsService
import ua.com.fleetwisor.core.data.local.settings.LocalSettingsServiceImpl
import ua.com.fleetwisor.core.domain.utils.validators.EmailPatternValidator
import ua.com.fleetwisor.core.domain.utils.validators.PatternValidator
import ua.com.fleetwisor.core.domain.utils.validators.UserDataValidator

val localModule = module {
    singleOf(::LocalAuthServiceImpl).bind<LocalAuthService>()
    singleOf(::LocalSettingsServiceImpl).bind<LocalSettingsService>()
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)

}