package ua.com.fleetwisor.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext
import ua.com.agroswit.theme.components.dialogs.ConfirmationDialog
import ua.com.fleetwisor.app.navigation.NavigationRoot
import ua.com.fleetwisor.app.navigation.graphs.AuthGraph
import ua.com.fleetwisor.app.navigation.graphs.MainMenuGraph
import ua.com.fleetwisor.app.navigation.routeClass
import ua.com.fleetwisor.core.data.local.LocalAuthService
import ua.com.fleetwisor.core.data.local.LocalAuthServiceImpl
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottomBar
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar.AgroswitBottomBarScreen
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar.screens
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo


class MainActivity : ComponentActivity() {
    private var localAuthService: LocalAuthService? = null
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }
        super.onCreate(savedInstanceState)
        localAuthService = LocalAuthServiceImpl(this)
        enableEdgeToEdge()
        setContent {
            val res = localAuthService?.getAuthInfo()
                ?.collectAsStateWithLifecycle(AuthInfo(refreshToken = "1"))
            val navController = rememberNavController()
            val backStack by navController.currentBackStackEntryAsState()
            val destination = backStack?.destination?.hierarchy?.first {
                it is NavGraph
            }
            val closestNavGraph = destination?.routeClass()
            val selectedRoute = screens.firstOrNull { it.graph::class == closestNavGraph }
            bottomBar = {
                AgroswitBottomBarScreen(
                    selectedRoute = selectedRoute?.graph ?: MainMenuGraph
                ) {

                    if (selectedRoute != null && selectedRoute != it) {
                        navController.navigate(it) {
                            popUpTo(selectedRoute.graph) {
                                saveState = true
                                inclusive = true
                            }
                            restoreState = true
                        }
                    }
                }
            }
            val startGraph = if (res?.value?.refreshToken?.isNotEmptyOrBlank() == true) {
                MainMenuGraph
            } else AuthGraph.Auth
            KoinContext {
                FleetWisorTheme {
                    FleetWisorScaffold(modifier = Modifier.fillMaxSize()) { _ ->
                        if (!viewModel.state.isCheckingAuth) {
                            if (viewModel.state.error != emptyUiText) {
                                ConfirmationDialog(
                                    text = viewModel.state.error.asString(),
                                    buttonText = "Ok"
                                ) {
                                    viewModel.dismissDialog()
                                }
                            }
                            NavigationRoot(
                                startDestination = startGraph,
                                navController = navController,
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        localAuthService = null
    }
}
