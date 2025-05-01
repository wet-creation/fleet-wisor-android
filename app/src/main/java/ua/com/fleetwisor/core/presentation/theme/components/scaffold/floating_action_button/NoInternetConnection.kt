package ua.com.agroswit.theme.components.scaffold.floating_action_button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.network.ConnectivityObserver
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun NoInternetConnection(
    modifier: Modifier = Modifier,
    status: ConnectivityObserver.Status
) {
    if (status != ConnectivityObserver.Status.Available)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.Top,
            modifier = modifier.background(
                color = FleetWisorTheme.colors.brandPrimaryExtraLight,
                shape = RoundedCornerShape(size = 6.dp)
            )
        ) {
            Text(
                text = stringResource(id = R.string.no_internet),
                style = FleetWisorTheme.typography.labelMediumR,
                color = FleetWisorTheme.colors.brandPrimaryNormal
            )
        }
}