package ua.com.fleetwisor.core.data.local.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ua.com.fleetwisor.core.data.local.LocalAuthService
import ua.com.fleetwisor.core.data.local.LocalAuthServiceImpl
import ua.com.fleetwisor.core.domain.utils.validators.EmailPatternValidator
import ua.com.fleetwisor.core.domain.utils.validators.PatternValidator
import ua.com.fleetwisor.core.domain.utils.validators.UserDataValidator

val localModule = module {
    singleOf(::LocalAuthServiceImpl).bind<LocalAuthService>()
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)

}