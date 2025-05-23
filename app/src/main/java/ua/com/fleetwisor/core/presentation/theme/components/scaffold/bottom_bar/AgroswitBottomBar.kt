package ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.noRippleClickable
import ua.com.fleetwisor.app.navigation.graphs.CarsGraph
import ua.com.fleetwisor.app.navigation.graphs.DriversGraph
import ua.com.fleetwisor.app.navigation.graphs.MainMenuGraph
import ua.com.fleetwisor.app.navigation.graphs.ProfileGraph

val screens = listOf(
    BottomNavBarMenu.Menu,
    BottomNavBarMenu.Cars,
    BottomNavBarMenu.Drivers,
    BottomNavBarMenu.Profile,
)
sealed class BottomNavBarMenu(
    @StringRes val title: Int,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val icon: Int,
    val graph: Any,
) {
    data object Menu : BottomNavBarMenu(
        title = (R.string.main_menu_text),
        graph = MainMenuGraph,
        icon = R.drawable.home_outlined,
        activeIcon = R.drawable.home_filled
    )
    data object Cars : BottomNavBarMenu(
        title = (R.string.cars_text),
        graph = CarsGraph,
        icon = R.drawable.cars_otlined,
        activeIcon = R.drawable.cars_filled
    )
    data object Drivers : BottomNavBarMenu(
        title = (R.string.drivers_text),
        graph = DriversGraph,
        icon = R.drawable.driver_outlined,
        activeIcon = R.drawable.driver_filled
    )
    data object Profile : BottomNavBarMenu(
        title = (R.string.profile_text),
        graph = ProfileGraph,
        icon = R.drawable.person_outlined,
        activeIcon = R.drawable.person_filled
    )
}

@Composable
fun AgroswitBottomBarScreen(
    selectedRoute: Any,
    onAction: (Any) -> Unit,
) {
    BottomAppBar(
        containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
        contentColor = FleetWisorTheme.colors.neutralPrimaryLight,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 4.dp)
    ) {
        screens.forEach {
            BottomNavBarItem(
                bottBottomNavBarMenu = it,
                active = selectedRoute == it.graph,
                modifier = Modifier
                    .weight(1f)
            ) {
                onAction(it.graph)
            }
        }
    }
}


@Composable
fun BottomNavBarItem(
    modifier: Modifier = Modifier,
    bottBottomNavBarMenu: BottomNavBarMenu,
    active: Boolean = false,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .noRippleClickable { onClick() }
            .padding(8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),

        ) {
        Icon(
            tint = FleetWisorTheme.colors.brandSecondaryNormal,
            painter = painterResource(id = if (active) bottBottomNavBarMenu.activeIcon else bottBottomNavBarMenu.icon),
            contentDescription = ""
        )
        Text(
            text = stringResource(bottBottomNavBarMenu.title),
            textAlign = TextAlign.Center,
            color = if (active) FleetWisorTheme.colors.brandSecondaryNormal else FleetWisorTheme.colors.neutralPrimaryLight,
            style = if (active) FleetWisorTheme.typography.labelMediumSB else FleetWisorTheme.typography.labelMediumR
        )
    }
}

@Preview
@Composable
private fun AgroswitBottomBarScreenPreview() {
    FleetWisorTheme {
        AgroswitBottomBarScreen(
            onAction = {},
            selectedRoute = BottomNavBarMenu.Menu.graph
        )
    }
}