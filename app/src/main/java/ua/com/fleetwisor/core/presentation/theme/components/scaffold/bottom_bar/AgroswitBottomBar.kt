package ua.com.fleetwisor.core.presentation.theme.components.scaffold.bottom_bar

//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import ua.com.agroswit.theme.FleetWisorTheme
//import ua.com.agroswit.theme.components.utils.noRippleClickable
//import ua.com.agroswit.ui.utils.UiText
//import ua.com.fleetwisor.core.presentation.ui.utils.UiText
//
//sealed class BottomNavBarMenu(
//    val title: UiText,
//    val route: String,
//    @DrawableRes val activeIcon: Int,
//    @DrawableRes val icon: Int
//) {
//    data object Catalog : BottomNavBarMenu(
//        title = UiText.StringResource(ua.com.agroswit.ui.R.string.product_text),
//        route = "catalog",
//        icon = ua.com.agroswit.theme.R.drawable.catalog_outlined,
//        activeIcon = ua.com.agroswit.theme.R.drawable.catalog_filled
//    )
//
//    data object Offers : BottomNavBarMenu(
//        title = UiText.StringResource(ua.com.agroswit.ui.R.string.promotional_offers_text_short),
//        route = "offers",
//        icon = ua.com.agroswit.theme.R.drawable.promotional_offers_outlined,
//        activeIcon = ua.com.agroswit.theme.R.drawable.promotion_offers_filled
//    )
//
//    data object Schemes : BottomNavBarMenu(
//        title = UiText.StringResource(ua.com.agroswit.ui.R.string.scheme_of_protection_short_text),
//        route = "schemes",
//        icon = ua.com.agroswit.theme.R.drawable.scheme_of_protection_outlined,
//        activeIcon = ua.com.agroswit.theme.R.drawable.scheme_of_protection_filled
//    )
//
//    data object Contacts : BottomNavBarMenu(
//        title = UiText.StringResource(ua.com.agroswit.ui.R.string.contacts_text),
//        route = "contacts",
//        icon = ua.com.agroswit.theme.R.drawable.contacts_outlined,
//        activeIcon = ua.com.agroswit.theme.R.drawable.contacts_filled
//    )
//}
//
//@Composable
//fun AgroswitBottomBarScreen(
//    selectedRoute: String,
//    onAction: (String) -> Unit,
//) {
//    val screens = listOf(
//        BottomNavBarMenu.Catalog,
//        BottomNavBarMenu.Offers,
//        BottomNavBarMenu.Schemes,
//        BottomNavBarMenu.Contacts,
//    )
//
//    BottomAppBar(
//        containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
//        contentColor = FleetWisorTheme.colors.neutralPrimaryLight,
//        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, top = 4.dp)
//
//
//    ) {
//        screens.forEach {
//            BottomNavBarItem(
//                bottBottomNavBarMenu = it,
//                active = selectedRoute == it.route,
//                modifier = Modifier
//                    .weight(1f)
//            ) {
//                onAction(it.route)
//            }
//        }
//    }
//}
//
//
//@Composable
//fun BottomNavBarItem(
//    modifier: Modifier = Modifier,
//    bottBottomNavBarMenu: BottomNavBarMenu,
//    active: Boolean = false,
//    onClick: () -> Unit
//) {
//    Column(
//        modifier = modifier
//            .noRippleClickable { onClick() }
//            .padding(8.dp)
//            .fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(8.dp),
//
//        ) {
//        Image(
//            painter = painterResource(id = if (active) bottBottomNavBarMenu.activeIcon else bottBottomNavBarMenu.icon),
//            contentDescription = ""
//        )
//        Text(
//            text = bottBottomNavBarMenu.title.asString(),
//            textAlign = TextAlign.Center,
//            color = FleetWisorTheme.colors.neutralPrimaryLight,
//            style = if (active) FleetWisorTheme.typography.labelMediumSB else FleetWisorTheme.typography.labelMediumR
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun AgroswitBottomBarScreenPreview() {
//    FleetWisorTheme {
//        AgroswitBottomBarScreen(
//            onAction = {},
//            selectedRoute = BottomNavBarMenu.Catalog.route
//        )
//    }
//}