package ua.com.fleetwisor.features.main_menu.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.dropdown.SelectedDropDownElement
import ua.com.agroswit.theme.components.scaffold.modal_botton_sheet.AgroswitModalBottomSheet
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.monthToString
import ua.com.fleetwisor.core.domain.utils.toMillis
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.bottom_sheets.SearchElements
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.CarSelectionButton
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DateRangePickerModal
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.features.main_menu.presentation.components.ReportTile

@Composable
fun MainMenuScreenRoot(
    viewModel: MainMenuViewModel = koinViewModel()
) {

    MainMenuScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainMenuScreen(
    state: MainMenuState,
    onAction: (MainMenuAction) -> Unit
) {
    var showDateDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.downloadState) {
        val downloadState = state.downloadState
        if (downloadState is DownloadState.Error)
            snackBarHostState.showSnackbar(
                downloadState.error.asString(context),
                duration = SnackbarDuration.Long
            )
        else if (downloadState is DownloadState.Success)
            snackBarHostState.showSnackbar(
                UiText.StringResource(R.string.download_succes).asString(context),
                duration = SnackbarDuration.Long
            )
    }
    FleetWisorScaffold(
        hasBottomBar = true,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        },
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.main_menu_text)
            )
        }
    ) { paddingValues ->
        val modalSheetBottom = remember {
            derivedStateOf {
                paddingValues.calculateBottomPadding() / 2
            }
        }
        if (state.isLoading) {
            Box(
                Modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = FleetWisorTheme.colors.brandPrimaryNormal)
            }
        }
        if (showDateDialog) {
            DateRangePickerModal(
                dateRangePickerState = rememberDateRangePickerState(
                    initialSelectedStartDateMillis = state.startDate.toMillis(),
                    initialSelectedEndDateMillis = state.endDate.toMillis(),
                ),
                onDateRangeSelected = {
                    if (it.first != null && it.second != null)
                        onAction(MainMenuAction.SelectPeriod(it.first!!, it.second!!))
                }
            ) {
                showDateDialog = false
            }
        }
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = 20.dp
                )
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AgroswitModalBottomSheet(
                isVisible = state.modalBottomSheetState.isOpen,
                onDismissRequest = {
                    onAction(MainMenuAction.OnModalSheetClose)
                },
            ) {

                if (state.modalBottomSheetState.showReportsList) {
                    SearchElements(
                        modifier = Modifier
                            .padding(vertical = 12.dp, horizontal = 20.dp)
                            .padding(bottom = modalSheetBottom.value),
                        title = stringResource(R.string.cars_text),
                        currentIndex = state.filteredReports.indexOf(state.selectedReport),
                        inputValue = state.searchCarValue,
                        elementsList = {
                            state.filteredReports.map { it.name }
                        },
                        inputSearch = {
                            onAction(MainMenuAction.SearchCars(it))
                        }
                    ) {
                        onAction(MainMenuAction.SelectCarForReport(it))
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CarSelectionButton(
                    text = state.selectedReport.name,
                ) {
                    onAction(MainMenuAction.ShowCarSearch)

                }
                Box(modifier = Modifier.size(28.dp)) {
                    if (state.downloadState == DownloadState.Downloading) {
                        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
                    } else
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    if (state.downloadState == DownloadState.Idle)
                                        onAction(MainMenuAction.DownloadExcel(context))
                                },
                            tint = FleetWisorTheme.colors.brandPrimaryNormal,
                            painter = when (state.downloadState) {
                                DownloadState.Downloading -> {
                                    FleetWisorTheme.icons.download
                                }

                                is DownloadState.Error -> FleetWisorTheme.icons.cross
                                DownloadState.Idle -> FleetWisorTheme.icons.download
                                DownloadState.Success -> FleetWisorTheme.icons.check
                            },
                            contentDescription = ""
                        )

                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.clickable {
                    showDateDialog = true

                }) {
                HorizontalDivider()
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Text("Від:", style = FleetWisorTheme.typography.bodyLarge)
                        Text("До:", style = FleetWisorTheme.typography.bodyLarge)
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),

                            ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.startDate.dayOfMonth.toString()
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.startDate.monthToString()
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.startDate.year.toString()
                            ) {}
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),

                            ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.endDate.dayOfMonth.toString()
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.endDate.monthToString()
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                active = false,
                                textItem = state.endDate.year.toString()
                            ) {}
                        }
                    }
                }
                HorizontalDivider()
            }
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Сума витрат",
                        value = state.selectedReport.totalSpending.toString(),
                        unit = "грн",
                        icon = FleetWisorTheme.icons.money,
                        iconColor = FleetWisorTheme.colors.brandSecondaryNormal,
                    )
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "К-сть заправок ",
                        value = state.selectedReport.fillUpCount.toString(),
                        icon = FleetWisorTheme.icons.gasMeter,
                        iconColor = FleetWisorTheme.colors.labelInProgress,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Вартість заправок",
                        value = state.selectedReport.totalFillUp.toString(),
                        unit = "грн",
                        icon = FleetWisorTheme.icons.gasStation,
                        iconColor = FleetWisorTheme.colors.herbicideColor,
                    )
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "К-сть обслуговувань",
                        value = state.selectedReport.fillUpCount.toString(),
                        icon = FleetWisorTheme.icons.tool,
                        iconColor = FleetWisorTheme.colors.brandPrimaryLight,
                    )

                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Вартість обслуговувань",
                        value = state.selectedReport.totalMaintenance.toString(),
                        unit = "грн",
                        icon = FleetWisorTheme.icons.homeService,
                        iconColor = FleetWisorTheme.colors.neutralSecondaryDark,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }

        }
    }
}

@Preview
@Composable
private fun MainMenuScreenPreview() {
    FleetWisorTheme {
        MainMenuScreen(
            state = MainMenuState(),
            onAction = {}
        )
    }
}