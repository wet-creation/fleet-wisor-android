package ua.com.fleetwisor.features.cars.presentation.maintenance.edit

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
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.TabInfo
import ua.com.fleetwisor.features.cars.presentation.maintenance.common.components.MaintenanceCheckTab
import ua.com.fleetwisor.features.cars.presentation.maintenance.common.components.MaintenanceInfo

@Composable
fun MaintenanceEditRoot(
    viewModel: MaintenanceEditViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MaintenanceEditScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                MaintenanceEditAction.NavigateBack -> navigateBack()
                else -> {}
            }
        }
    )
}

@Composable
fun MaintenanceEditScreen(
    state: MaintenanceEditState,
    onAction: (MaintenanceEditAction) -> Unit,
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
    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.maintenance_edit_text)
            ) {
                onAction(MaintenanceEditAction.NavigateBack)
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
                            onAction(MaintenanceEditAction.ChangeTabIndex(index))
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
        MaintenanceEditScreen(
            state = MaintenanceEditState(),
            onAction = {}
        )
    }
}