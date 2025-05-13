package ua.com.fleetwisor.features.cars.presentation.cars.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.dialogs.ConfirmationDialog
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.ObserverAsEvents
import ua.com.fleetwisor.core.presentation.ui.utils.TabInfo
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.features.cars.presentation.cars.common.compoents.CarInfoTab
import ua.com.fleetwisor.features.cars.presentation.cars.common.compoents.InsuranceTab


@Composable
fun CarCreateRoot(
    viewModel: CarCreateViewModel = koinViewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserverAsEvents(viewModel.events) { event ->
        if (event)
            navigateBack()
    }
    CarCreateScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                CarCreateAction.NavigateBack -> {
                    navigateBack()
                }

                else -> {}
            }
        }
    )
}

@Composable
fun CarCreateScreen(
    state: CarCreateState,
    onAction: (CarCreateAction) -> Unit,
) {
    var paddingValue by remember { mutableStateOf(PaddingValues()) }
    if (state.error != emptyUiText)
        ConfirmationDialog(
            text = state.error.asString(),
            buttonText = stringResource(id = R.string.confirm)
        ) {
            onAction(CarCreateAction.DismissErrorDialog)
        }
    val tabs = listOf(
        TabInfo(
            title = stringResource(R.string.general_tab_name),
            content = {
                CarInfoTab(
                    canBeSaved = state.canBeSaved,
                    drivers = state.driversFilter.toImmutableList(),
                    car = state.car, onAction = onAction,
                    fuelTypes = state.fuelTypes.toImmutableList(),
                    carBodies = state.carBodies.toImmutableList(),
                    paddingValue = paddingValue,
                    driverSearchValue = state.driverSearchValue,
                    selectedDrivers = state.selectedDrivers.toImmutableList(),
                )
            }
        ),
        TabInfo(
            title = stringResource(R.string.insurance_text),
            content = {
                InsuranceTab(
                    insurance = state.insurance,
                    selectedPhoto = state.selectedPhoto,
                    onAction = onAction
                )
            }
        ),

        )
    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.car_create_text)
            ) {
                onAction(CarCreateAction.NavigateBack)
            }
        },
        hasBottomBar = true,
    ) { paddingValues ->
        paddingValue = paddingValues
        Column(
            modifier = Modifier.padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            TabRow(
                modifier = Modifier
                    .height(40.dp), selectedTabIndex = state.selectedTab,
                containerColor = FleetWisorTheme.colors.brandPrimaryMedium,
                indicator = {},
                divider = {}
            ) {
                tabs.forEachIndexed { index, tabItem ->
                    Tab(
                        selected = index == state.selectedTab,
                        onClick = {
                            onAction(CarCreateAction.ChangeTabIndex(index))
                        }) {
                        Text(
                            text = tabItem.title,
                            style = FleetWisorTheme.typography.labelLargeM,
                            color = if (index != state.selectedTab)
                                FleetWisorTheme.colors.brandPrimaryLight
                            else FleetWisorTheme.colors.neutralPrimaryLight
                        )
                    }
                }
            }
            tabs[state.selectedTab].content()
        }

    }

}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        CarCreateScreen(
            state = CarCreateState(),
            onAction = {}
        )
    }
}