package ua.com.fleetwisor.features.cars.presentation.maintenance.create

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
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.TabInfo
import ua.com.fleetwisor.features.cars.presentation.fill_up.common.components.FillUpCheckTab
import ua.com.fleetwisor.features.cars.presentation.fill_up.common.components.FillUpInfo
import ua.com.fleetwisor.features.cars.presentation.fill_up.create.FillUpCreateAction
import ua.com.fleetwisor.features.cars.presentation.maintenance.common.components.MaintenanceCheckTab
import ua.com.fleetwisor.features.cars.presentation.maintenance.common.components.MaintenanceInfo

@Composable
fun MaintenanceCreateRoot(
    viewModel: MaintenanceCreateViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MaintenanceCreateScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when(it){
                MaintenanceCreateAction.NavigateBack -> navigateBack()
                else -> {}
            }
        }
    )
}

@Composable
fun MaintenanceCreateScreen(
    state: MaintenanceCreateState,
    onAction: (MaintenanceCreateAction) -> Unit,
) {
    val tabs = listOf(
        TabInfo(
            title = stringResource(R.string.general_tab_name),
            content = {
                MaintenanceInfo(
                    maintenance = state.maintenance, onAction = onAction,
                )
            }
        ),
        TabInfo(
            title = stringResource(R.string.check_text),
            content = {
                MaintenanceCheckTab(maintenance = state.maintenance, onAction = onAction)
            }
        ),

        )
    AgroswitScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.maintenance_create_text)
            ) {
                onAction(MaintenanceCreateAction.NavigateBack)
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
                            onAction(MaintenanceCreateAction.ChangeTabIndex(index))
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
        MaintenanceCreateScreen(
            state = MaintenanceCreateState(),
            onAction = {}
        )
    }
}