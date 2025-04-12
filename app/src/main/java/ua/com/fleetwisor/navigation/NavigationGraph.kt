package ua.com.fleetwisor.navigation

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ua.com.fleetwisor.features.auth.presentation.auth.AuthScreenRoot
import ua.com.fleetwisor.features.auth.presentation.login.LoginScreenRoot
import ua.com.fleetwisor.features.auth.presentation.register.RegisterScreenRoot
import ua.com.fleetwisor.features.cars.presentation.cars.create.CarCreateRoot
import ua.com.fleetwisor.features.cars.presentation.cars.edit.CarEditRoot
import ua.com.fleetwisor.features.cars.presentation.cars.list.CarsListRoot
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateRoot
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditAction
import ua.com.fleetwisor.features.cars.presentation.fill_up.edit.FillUpEditRoot
import ua.com.fleetwisor.features.cars.presentation.fill_up.list.FilUpListRoot
import ua.com.fleetwisor.features.cars.presentation.main.CarMainRoot
import ua.com.fleetwisor.features.cars.presentation.maintenance.list.MaintenanceListRoot
import ua.com.fleetwisor.features.drivers.presentation.create.DriverCreateRoot
import ua.com.fleetwisor.features.drivers.presentation.main.DriversListRoot
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
                navController.navigate(MainMenuGraph) {
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
    navigation<ProfileGraph>(
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
    navigation<MainMenuGraph>(
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
    navigation<CarsGraph>(
        startDestination = CarsGraph.CarMain,
    ) {
        composable<CarsGraph.CarMain> {
            CarMainRoot(
                navigateCars = {
                    navController.navigate(CarsGraph.CarList)
                },
                navigateMaintenance = {
                    navController.navigate(CarsGraph.Maintenance)

                },
                navigateFillUp = {
                    navController.navigate(CarsGraph.FillUp)

                }
            )
        }
        composable<CarsGraph.CarList> {
            CarsListRoot(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateEdit = {
                    navController.navigate(CarsGraph.CarEdit)
                },
                navigateCreate = {
                    navController.navigate(CarsGraph.CarCreate)
                }
            )
        }
        composable<CarsGraph.CarCreate> {
            CarCreateRoot(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<CarsGraph.CarEdit> {
            CarEditRoot(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable<CarsGraph.FillUp> {
            FilUpListRoot(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateEdit = {
                    navController.navigate(CarsGraph.FillUpEdit)

                },
                navigateCreate = {
                    navController.navigate(CarsGraph.FillUpCreate)

                }
            )
        }
        composable<CarsGraph.FillUpCreate> {
            FillUpCreateRoot(
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable<CarsGraph.FillUpEdit> {
            FillUpEditRoot(
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }

        composable<CarsGraph.Maintenance> {
            MaintenanceListRoot(
                navigateBack = {
                    navController.navigateUp()
                },
                navigateEdit = {},
                navigateCreate = { }
            )
        }

    }
}

private fun NavGraphBuilder.driversGraph(
    navController: NavHostController,
) {
    navigation<DriversGraph>(
        startDestination = DriversGraph.Driver,
    ) {
        composable<DriversGraph.Driver> {
            DriversListRoot(
                navigateEdit = {

                },
                navigateCreate = {
                    navController.navigate(DriversGraph.Create)
                }
            )
        }
        composable<DriversGraph.Create> {
            DriverCreateRoot(
                navigateBack = {
                    navController.navigateUp()
                }
            )
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
