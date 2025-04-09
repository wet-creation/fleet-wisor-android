package ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.noRippleClickable

sealed class BottomNavBarMenu(
    val title: UiText,
    @DrawableRes val activeIcon: Int,
    @DrawableRes val icon: Int
) {
    data object Menu : BottomNavBarMenu(
        title = UiText.StringResource(R.string.main_menu_text),
        icon = R.drawable.home_outlined,
        activeIcon = R.drawable.home_filled
    )

    data object Cars : BottomNavBarMenu(
        title = UiText.StringResource(R.string.cars_text),
        icon = R.drawable.cars_otlined,
        activeIcon = R.drawable.car_filled
    )

    data object Drivers : BottomNavBarMenu(
        title = UiText.StringResource(R.string.drivers_text),
        icon = R.drawable.driver_outlined,
        activeIcon = R.drawable.driver_filled
    )

    data object Profile : BottomNavBarMenu(
        title = UiText.StringResource(R.string.profile_text),
        icon = R.drawable.person_outlined,
        activeIcon = R.drawable.person_filled
    )
}

@Composable
fun AgroswitBottomBarScreen(
    selectedRoute: Any,
    onAction: (Any) -> Unit,
) {
    val screens = listOf(
        BottomNavBarMenu.Menu,
        BottomNavBarMenu.Cars,
        BottomNavBarMenu.Drivers,
        BottomNavBarMenu.Profile,
    )

    BottomAppBar(
        containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
        contentColor = FleetWisorTheme.colors.neutralPrimaryLight,
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 4.dp)


    ) {
        screens.forEach {
            BottomNavBarItem(
                bottBottomNavBarMenu = it,
                active = selectedRoute == it,
                modifier = Modifier
                    .weight(1f)
            ) {
                onAction(it)
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
            text = bottBottomNavBarMenu.title.asString(),
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
            selectedRoute = BottomNavBarMenu.Menu
        )
    }
}