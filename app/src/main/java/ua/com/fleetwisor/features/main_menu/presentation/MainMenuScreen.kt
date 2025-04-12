package ua.com.fleetwisor.features.main_menu.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.dropdown.SelectedDropDown
import ua.com.agroswit.theme.components.dropdown.SelectedDropDownElement
import ua.com.agroswit.theme.components.select_controls.DropDownItemState
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.CarSelectionButton
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
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

@Composable
private fun MainMenuScreen(
    state: MainMenuState,
    onAction: (MainMenuAction) -> Unit
) {
    AgroswitScaffold(
        hasBottomBar = true,
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.main_menu_text)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CarSelectionButton(
                text = "Всі Машини",

            ) {

            }
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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
                            modifier = Modifier.clickable {

                            }
                        ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "12"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "лютий"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "2025"
                            ) {}
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.clickable {
                            }
                        ) {
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "12"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "березень"
                            ) {}
                            SelectedDropDownElement(
                                modifier = Modifier.weight(1f),
                                textItem = "2025"
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
                        value = "7777",
                        unit = "грн",
                        icon = FleetWisorTheme.icons.money,
                        iconColor = FleetWisorTheme.colors.brandSecondaryNormal,
                    )
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "К-сть заправок ",
                        value = "50",
                        unit = "",
                        icon = FleetWisorTheme.icons.gasMeter,
                        iconColor = FleetWisorTheme.colors.labelInProgress,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Вартість заправок",
                        value = "5000",
                        unit = "грн",
                        icon = FleetWisorTheme.icons.gasStation,
                        iconColor = FleetWisorTheme.colors.herbicideColor,
                    )
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Обсяг пального",
                        value = "50",
                        unit = "л",
                        icon = FleetWisorTheme.icons.gasFluid,
                        iconColor = FleetWisorTheme.colors.microFertilizerColor,
                    )

                }
                Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {

                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "К-сть обслуговувань",
                        value = "5",
                        unit = "",
                        icon = FleetWisorTheme.icons.tool,
                        iconColor = FleetWisorTheme.colors.brandPrimaryLight,
                    )
                    ReportTile(
                        modifier = Modifier.weight(1f),
                        reportText = "Вартість обслуговувань",
                        value = "7777",
                        unit = "грн",
                        icon = FleetWisorTheme.icons.homeService,
                        iconColor = FleetWisorTheme.colors.neutralSecondaryDark,
                    )
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