package ua.com.fleetwisor.app.di

import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import ua.com.fleetwisor.app.FleetWisorApp

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as FleetWisorApp).applicationScope
    }
}