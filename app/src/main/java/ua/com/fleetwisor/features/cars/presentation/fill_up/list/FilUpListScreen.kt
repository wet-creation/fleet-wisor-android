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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.fields.TextFieldAgroswit
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.formatTime
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.dialogs.ConfirmationDialog
import ua.com.fleetwisor.core.presentation.theme.components.items.FillUpListItem
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText

@Composable
fun FilUpListRoot(
    viewModel: FillUpListViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateEdit: (Int) -> Unit,
    navigateCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    FilUpListScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                FilUpListAction.NavigateBack -> navigateBack()
                FilUpListAction.NavigateCreate -> navigateCreate()
                is FilUpListAction.NavigateEdit -> navigateEdit(it.id)
                else -> {}
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilUpListScreen(
    state: FilUpListState,
    onAction: (FilUpListAction) -> Unit,
) {
    val pullToRefreshState = rememberPullToRefreshState()

    if (state.error != emptyUiText)
        ConfirmationDialog(
            text = state.error.asString(),
            buttonText = stringResource(id = R.string.confirm)
        ) {
            onAction(FilUpListAction.DismissErrorDialog)
        }
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
                value = state.searchValue,
                hint = stringResource(R.string.search_text),
                onValueChange = {
                    onAction(FilUpListAction.InputSearch(it))
                }
            )
            PullToRefreshBox(
                state = pullToRefreshState,
                isRefreshing = state.isLoading,
                onRefresh = {
                    onAction(FilUpListAction.Refresh)
                },
                indicator = {
                    Indicator(
                        modifier = Modifier.align(Alignment.TopCenter),
                        isRefreshing = state.isLoading,
                        containerColor = FleetWisorTheme.colors.brandPrimaryNormal,
                        color = FleetWisorTheme.colors.brandSecondaryNormal,
                        state = pullToRefreshState
                    )

                }
            ) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        Spacer(Modifier)
                    }
                    items(state.filteredFillUps) { fillUp ->
                        FillUpListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onAction(FilUpListAction.NavigateEdit(fillUp.id))
                                },
                            title = fillUp.car.name,
                            firstText = fillUp.time.formatTime(),
                            secondText = fillUp.price.toString()
                        )
                    }
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