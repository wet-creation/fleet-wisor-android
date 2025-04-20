package ua.com.fleetwisor.app

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ua.com.fleetwisor.app.di.appModule
import ua.com.fleetwisor.app.di.mainModule
import ua.com.fleetwisor.core.data.local.di.localModule
import ua.com.fleetwisor.core.data.network.di.networkModule
import ua.com.fleetwisor.features.auth.di.authPresentationModule
import ua.com.fleetwisor.features.main_menu.di.mainMenuModule
import ua.com.fleetwisor.features.profile.di.profileModule

class FleetWisorApp: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FleetWisorApp)
            modules(
                appModule,
                localModule,
                networkModule,
                authPresentationModule,
                mainMenuModule,
                profileModule,
                mainModule
            )
        }
    }
}