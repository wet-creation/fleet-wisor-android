package ua.com.fleetwisor.features.cars.presentation.maintenance.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.agroswit.theme.components.fields.TextFieldAgroswit
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.items.MaintenanceListItem
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar

@Composable
fun MaintenanceListRoot(
    viewModel: MaintenanceListViewModel = viewModel(),
    navigateBack: () -> Unit,
    navigateEdit: (Int) -> Unit,
    navigateCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    MaintenanceListScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                MaintenanceListAction.NavigateBack -> navigateBack()
                MaintenanceListAction.NavigateCreate -> navigateCreate()
                is MaintenanceListAction.NavigateEdit -> navigateEdit(it.id)
            }
        }
    )
}

@Composable
fun MaintenanceListScreen(
    state: MaintenanceListState,
    onAction: (MaintenanceListAction) -> Unit,
) {
    AgroswitScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.drivers_text)
            ) {
                onAction(MaintenanceListAction.NavigateBack)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
                modifier = Modifier.size(64.dp),
                onClick = {
                    onAction(MaintenanceListAction.NavigateCreate)
                },
                shape = CircleShape
            ) {
                Icon(
                    modifier = Modifier.size(48.dp),
                    painter = FleetWisorTheme.icons.plus,
                    tint = FleetWisorTheme.colors.brandSecondaryNormal,
                    contentDescription = ""
                )
            }
        },
        hasBottomBar = true,
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp)
        ) {
            TextFieldAgroswit(
                icon = FleetWisorTheme.icons.search,
                value = "",
                hint = "Пошук водія",
                onValueChange = {}
            )
            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                item {
                    Spacer(Modifier)
                }
                items(state.maintenances) { maintenance ->
                    MaintenanceListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAction(MaintenanceListAction.NavigateEdit(maintenance.id))
                            },
                        title = "${maintenance.car.color} ${maintenance.car.brandName} ${maintenance.car.model}",
                        firstText = maintenance.time,
                        secondText = maintenance.price.toString()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        MaintenanceListScreen(
            state = MaintenanceListState(),
            onAction = {}
        )
    }
}