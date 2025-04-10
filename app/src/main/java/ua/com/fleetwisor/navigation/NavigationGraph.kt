package ua.com.fleetwisor.navigation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar.BottomNavBarMenu
import ua.com.fleetwisor.features.auth.presentation.auth.AuthScreenRoot
import ua.com.fleetwisor.features.auth.presentation.login.LoginScreenRoot
import ua.com.fleetwisor.features.auth.presentation.register.RegisterScreenRoot
import ua.com.fleetwisor.features.cars.presentation.main.CarMainRoot
import ua.com.fleetwisor.features.main_menu.presentation.MainMenuScreenRoot
import ua.com.fleetwisor.features.profile.presentation.ProfileRoot
import ua.com.fleetwisor.navigation.graphs.AuthGraph
import ua.com.fleetwisor.navigation.graphs.CarsGraph
import ua.com.fleetwisor.navigation.graphs.DriversGraph
import ua.com.fleetwisor.navigation.graphs.MainMenuGraph
import ua.com.fleetwisor.navigation.graphs.ProfileGraph
import kotlin.reflect.KClass


@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AuthGraph.Auth,
    ) {
        composable<AuthGraph.Auth> {
            AuthScreenRoot(
                navigateLogin = {
                    navController.navigate(AuthGraph.Login)
                },
                navigateRegister = {
                    navController.navigate(AuthGraph.Register)

                }
            )
        }
        composable<AuthGraph.Login> {
            LoginScreenRoot {
                navController.navigate(BottomNavBarMenu.Menu) {
                    popUpTo(0)
                }
            }
        }
        composable<AuthGraph.Register> {
            RegisterScreenRoot {

                navController.navigate(AuthGraph.Login)
            }
        }

        profileGraph(navController)
        carsGraph(navController)
        driversGraph(navController)
        mainMenuGraph(navController)
    }
}

private fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
) {
    navigation<BottomNavBarMenu.Profile>(
        startDestination = ProfileGraph.Profile,
    ) {
        composable<ProfileGraph.Profile> {
            ProfileRoot()
        }

    }
}


private fun NavGraphBuilder.mainMenuGraph(
    navController: NavHostController,
) {
    navigation<BottomNavBarMenu.Menu>(
        startDestination = MainMenuGraph.MainMenu,
    ) {
        composable<MainMenuGraph.MainMenu> {
            MainMenuScreenRoot()
        }

    }
}

private fun NavGraphBuilder.carsGraph(
    navController: NavHostController,
) {
    navigation<BottomNavBarMenu.Cars>(
        startDestination = CarsGraph.CarMain,
    ) {
        composable<CarsGraph.CarMain> {
            CarMainRoot()
        }

    }
}

private fun NavGraphBuilder.driversGraph(
    navController: NavHostController,
) {
    navigation<BottomNavBarMenu.Drivers>(
        startDestination = DriversGraph.Driver,
    ) {
        composable<DriversGraph.Driver> {
            Text("Hello Drivers")
        }

    }
}


fun NavDestination.routeClass(): KClass<*>? {
    return this
        .route
        ?.split("/")
        ?.first()
        ?.let { className ->
            generateSequence(className, ::replaceDotByDollar).mapNotNull(::tryParseClass)
                .firstOrNull()
        }
}

fun NavBackStackEntry.routeClass(): KClass<*>? {
    return this.destination.routeClass()
}

private fun tryParseClass(className: String): KClass<*>? {
    return runCatching { Class.forName(className).kotlin }.getOrNull()
}

private fun replaceDotByDollar(input: String): String? {
    val index = input.lastIndexOf('.')
    return if (index != -1) {
        String(input.toCharArray().apply { set(index, '$') })
    } else null
}

@Composable
fun AnimatedNavHost(
    navController: NavHostController,
    startDestination: Any,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = builder,
        enterTransition = {
            fadeIn(animationSpec = tween(500))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500))
        },
        popEnterTransition = {
            scaleIn(
                animationSpec = tween(
                    durationMillis = 100,
                    delayMillis = 35,
                ),
                initialScale = 1.1F,
            ) + fadeIn(
                animationSpec = tween(
                    durationMillis = 100,
                    delayMillis = 35,
                ),
            )
        },
        popExitTransition = {
            scaleOut(
                targetScale = 0.9F,
            ) + fadeOut(
                animationSpec = tween(
                    durationMillis = 35,
                    easing = CubicBezierEasing(0.1f, 0.1f, 0f, 1f),
                ),
            )
        },
    )
}
