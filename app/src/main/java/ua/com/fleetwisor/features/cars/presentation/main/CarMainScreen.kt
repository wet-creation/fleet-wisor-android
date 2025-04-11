package ua.com.fleetwisor.features.cars.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryLargeButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar

@Composable
fun CarMainRoot(
    viewModel: CarMainViewModel = viewModel(),
    navigateCars: () -> Unit,
    navigateMaintenance: () -> Unit,
    navigateFillUp: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CarMainScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)

            when (it) {
                CarMainAction.NavigateCars -> navigateCars()
                CarMainAction.NavigateFillUp -> navigateFillUp()
                CarMainAction.NavigateMaintenance -> navigateMaintenance()
            }
        }
    )
}

@Composable
fun CarMainScreen(
    state: CarMainState,
    onAction: (CarMainAction) -> Unit,
) {
    AgroswitScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.cars_text)
            )
        },
        hasBottomBar = true,
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp)
        ) {

            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                PrimaryLargeButton(
                    tint = FleetWisorTheme.colors.brandSecondaryNormal,
                    modifier = Modifier.weight(1f),
                    icon = FleetWisorTheme.icons.carRepair,
                    contentDescription = stringResource(R.string.car_maintenance),
                    text = stringResource(R.string.car_maintenance),
                ) {
                    onAction(CarMainAction.NavigateMaintenance)
                }
                PrimaryLargeButton(
                    tint = FleetWisorTheme.colors.brandSecondaryNormal,
                    modifier = Modifier.weight(1f),
                    icon = FleetWisorTheme.icons.gasStation,
                    contentDescription = stringResource(R.string.fillup),
                    text = stringResource(R.string.fillup),
                ) {
                    onAction(CarMainAction.NavigateFillUp)

                }
            }
            PrimaryLargeButton(
                tint = FleetWisorTheme.colors.brandSecondaryNormal,
                icon = FleetWisorTheme.icons.car,
                contentDescription = stringResource(R.string.cars_text),
                text = stringResource(R.string.cars_text),
            ) {
                onAction(CarMainAction.NavigateCars)

            }

        }

    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        CarMainScreen(
            state = CarMainState(),
            onAction = {}
        )
    }
}