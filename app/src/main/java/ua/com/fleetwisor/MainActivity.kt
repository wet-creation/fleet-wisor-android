package ua.com.fleetwisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottomBar
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar.AgroswitBottomBarScreen
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar.BottomNavBarMenu
import ua.com.fleetwisor.navigation.NavigationRoot
import ua.com.fleetwisor.navigation.routeClass

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val backStack by navController.currentBackStackEntryAsState()
            val destination = backStack?.destination?.hierarchy?.first {
                it is NavGraph
            }
            val selectedRoute = destination?.routeClass() ?: BottomNavBarMenu.Menu
            bottomBar = {
                AgroswitBottomBarScreen(
                    selectedRoute = selectedRoute
                ) {
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
            FleetWisorTheme {
                AgroswitScaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    NavigationRoot(
                        navController = navController,
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FleetWisorTheme {
        Greeting("Android")
    }
}