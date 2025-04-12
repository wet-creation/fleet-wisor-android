package ua.com.fleetwisor.features.cars.presentation.cars.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toImmutableList
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.TabInfo
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FuelType
import ua.com.fleetwisor.features.cars.presentation.cars.common.compoents.CarInfoTab
import ua.com.fleetwisor.features.cars.presentation.cars.common.compoents.InsuranceTab


@Composable
fun CarCreateRoot(
    viewModel: CarCreateViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
    val tabs = listOf(
        TabInfo(
            title = stringResource(R.string.general_tab_name),
            content = {
                CarInfoTab(
                    car = state.car, onAction = onAction,
                    fuelTypes = listOf<FuelType>(
                        FuelType(),
                        FuelType(),
                    ).toImmutableList(),
                    carBodies = listOf<CarBody>(
                        CarBody(),
                        CarBody(),
                        CarBody(),
                    ).toImmutableList(),
                )
            }
        ),
        TabInfo(
            title = stringResource(R.string.insurance_text),
            content = {
                InsuranceTab(insurance = state.insurance, onAction = onAction)
            }
        ),

        )
    AgroswitScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.car_create_text)
            ) {
                onAction(CarCreateAction.NavigateBack)
            }
        },
        hasBottomBar = true,
    ) { paddingValues ->
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
                divider = {}) {
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