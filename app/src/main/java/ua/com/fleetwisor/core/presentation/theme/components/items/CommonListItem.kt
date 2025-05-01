package ua.com.fleetwisor.core.presentation.theme.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
private fun CommonListItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: Painter,
    hintFirst: String,
    firstText: String,
    hintSecond: String,
    secondText: String
) {
    Row(
        modifier = modifier
            .border(
                1.dp,
                color = FleetWisorTheme.colors.neutralSecondaryDark,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(56.dp),
            painter = icon,
            tint = FleetWisorTheme.colors.neutralSecondaryDark,
            contentDescription = ""
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                title,
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                style = FleetWisorTheme.typography.headlineSmall
            )
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    "$hintFirst:",
                    color = FleetWisorTheme.colors.neutralSecondaryMedium,
                    style = FleetWisorTheme.typography.labelLargeR
                )
                Text(
                    firstText,
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    style = FleetWisorTheme.typography.titleMedium
                )

            }
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    "$hintSecond:",
                    color = FleetWisorTheme.colors.neutralSecondaryMedium,
                    style = FleetWisorTheme.typography.labelLargeR
                )
                Text(
                    secondText,
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    style = FleetWisorTheme.typography.titleMedium
                )
            }
        }

    }
}

@Composable
fun DriverListItem(
    modifier: Modifier = Modifier,
    title: String,
    firstText: String,
    secondText: String
) {
    CommonListItem(
        modifier = modifier,
        title = title,
        icon = FleetWisorTheme.icons.profile,
        hintFirst = stringResource(R.string.phone),
        firstText = firstText,
        hintSecond = stringResource(R.string.driver_licence_number_short_text),
        secondText = secondText
    )
}

@Composable
fun CarListItem(
    modifier: Modifier = Modifier,
    title: String,
    firstText: String,
    secondText: String
) {
    CommonListItem(
        modifier = modifier,
        title = title,
        icon = FleetWisorTheme.icons.car,
        hintFirst = stringResource(R.string.odometr_text),
        firstText = firstText,
        hintSecond = stringResource(R.string.car_number_text),
        secondText = secondText
    )
}

@Composable
fun FillUpListItem(
    modifier: Modifier = Modifier,
    title: String,
    firstText: String,
    secondText: String
) {
    CommonListItem(
        modifier = modifier,
        title = title,
        icon = FleetWisorTheme.icons.gasStation,
        hintFirst = stringResource(R.string.fill_up_date_text),
        firstText = firstText,
        hintSecond = stringResource(R.string.sum_text),
        secondText = secondText + " " + stringResource(R.string.currency_uah_text)
    )
}

@Composable
fun MaintenanceListItem(
    modifier: Modifier = Modifier,
    title: String,
    firstText: String,
    secondText: String
) {
    CommonListItem(
        modifier = modifier,
        title = title,
        icon = FleetWisorTheme.icons.homeService,
        hintFirst = stringResource(R.string.maintenance_date_text),
        firstText = firstText,
        hintSecond = stringResource(R.string.sum_text),
        secondText = secondText + " " + stringResource(R.string.currency_uah_text)
    )
}


@Preview
@Composable
private fun CommonListItemPrev() {
    MaintenanceListItem(
        title = "Вадим Мармеладов",
        firstText = "+380672898920",
        secondText = "147819"
    )
}