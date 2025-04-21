package ua.com.fleetwisor.features.cars.presentation.fill_up.list

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
import ua.com.fleetwisor.core.presentation.theme.components.items.FillUpListItem
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar

@Composable
fun FilUpListRoot(
    viewModel: FilUpListViewModel = viewModel(),
    navigateBack: () -> Unit,
    navigateEdit: (Int) -> Unit,
    navigateCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FilUpListScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when(it) {
                FilUpListAction.NavigateBack -> navigateBack()
                FilUpListAction.NavigateCreate -> navigateCreate()
                is FilUpListAction.NavigateEdit -> navigateEdit(it.id)
            }
        }
    )
}

@Composable
fun FilUpListScreen(
    state: FilUpListState,
    onAction: (FilUpListAction) -> Unit,
) {
    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.fillup)
            ) {
                onAction(FilUpListAction.NavigateBack)
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
                modifier = Modifier.size(64.dp),
                onClick = {
                    onAction(FilUpListAction.NavigateCreate)

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
                items(state.fillUps) { fillUp ->
                    FillUpListItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onAction(FilUpListAction.NavigateEdit(fillUp.id))
                            },
                        title = "${fillUp.car.color} ${fillUp.car.brandName} ${fillUp.car.model}",
                        firstText = fillUp.time,
                        secondText = fillUp.price.toString()
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
        FilUpListScreen(
            state = FilUpListState(),
            onAction = {}
        )
    }
}