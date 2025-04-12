package ua.com.fleetwisor.features.drivers.presentation.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar

@Composable
fun DriverCreateRoot(
    viewModel: DriverCreateViewModel = viewModel(),
    navigateBack: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DriverCreateScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                else -> navigateBack()
            }
        }
    )
}

@Composable
private fun DriverCreateScreen(
    state: DriverCreateState,
    onAction: (DriverCreateAction) -> Unit,
) {
    AgroswitScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.drivers_text)
            ) {
                onAction(DriverCreateAction.NavigateBack)
            }
        },
        hasBottomBar = true,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.personal_data_driver_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                Column {
                    LabelTextField(
                        icon = FleetWisorTheme.icons.person,
                        text = "",
                        placeholder = stringResource(R.string.surname) + "*",
                        hasLine = true
                    ) { }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.fop,
                        text = "",
                        placeholder = stringResource(R.string.name) + "*",
                        hasLine = true
                    ) { }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.phone,
                        text = "",
                        placeholder = stringResource(R.string.phone) + "*",
                        hasLine = true
                    ) { }
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.calendar,
                        text = "",
                        placeholder = stringResource(R.string.birtday) + "*",
                        hasLine = true
                    ) { }

                    TitledLabelTextField(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        icon = FleetWisorTheme.icons.paid,
                        text = "",
                        unitText = stringResource(R.string.currency_uah_text),
                        placeholder = "100",
                        titleText = stringResource(R.string.payment_for_month) + "*:",
                        onChange = {}
                    )

                }
                HorizontalDivider()
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.drvier_card_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                TitledLabelTextField(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.numbers,
                    text = "",
                    titleText = stringResource(R.string.driver_licence_number_text) + "*:",
                    placeholder = "0123",
                    onChange = {}
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondaryLongIconButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.front_page_document),
                        icon = FleetWisorTheme.icons.logout,
                        contentDescription = ""
                    ) { }
                    SecondaryLongIconButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.back_page_document),
                        icon = FleetWisorTheme.icons.logout,
                        contentDescription = ""
                    ) { }
                }
            }
            PrimaryButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.save_text),
                enabled = true,
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
            ) {
                onAction(DriverCreateAction.SaveDriver)
            }

        }
    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        DriverCreateScreen(
            state = DriverCreateState(),
            onAction = {}
        )
    }
}