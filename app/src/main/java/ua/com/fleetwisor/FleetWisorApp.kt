package ua.com.fleetwisor

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ua.com.fleetwisor.features.auth.di.authModule
import ua.com.fleetwisor.features.main_menu.di.mainMenuModule

class FleetWisorApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FleetWisorApp)
            modules(
                authModule,
                mainMenuModule
            )
        }
    }
}