package ua.com.fleetwisor.features.drivers.presentation.create

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.core.presentation.theme.components.dialogs.ConfirmationDialog
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.TitledLabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.images.BorderImage
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.theme.components.select_controls.DatePickerModal
import ua.com.fleetwisor.core.presentation.ui.utils.ObserverAsEvents
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText
import ua.com.fleetwisor.core.presentation.ui.utils.rememberAsyncImagePainter
import java.time.LocalDate

@Composable
fun DriverCreateRoot(
    viewModel: DriverCreateViewModel = koinViewModel(),
    navigateBack: () -> Unit,
    navigateCreate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserverAsEvents(viewModel.events) { event ->
        if (event)
            navigateCreate()
    }
    DriverCreateScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                DriverCreateAction.NavigateBack -> navigateBack()
                else -> {}
            }
        }
    )
}

@Composable
private fun DriverCreateScreen(
    state: DriverCreateState,
    onAction: (DriverCreateAction) -> Unit,
) {
    val context = LocalContext.current
    val pickFrontPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onAction(DriverCreateAction.SelectFrontPhoto(uri))
        }
    }
    val pickBackPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onAction(DriverCreateAction.SelectBackPhoto(uri))
        }
    }

    if (state.isDateSelectorOpen) {
        DatePickerModal(
            selectedDate = state.selectedBirthDay,
            onDismiss = {
                onAction(DriverCreateAction.DismissDateSelector)
            },
            onDateSelected = {
                onAction(DriverCreateAction.SelectBirthDay(it ?: LocalDate.now()))
            }
        )
    }
    if (state.error != emptyUiText)
        ConfirmationDialog(
            text = state.error.asString(),
            buttonText = stringResource(id = R.string.retry_text)
        ) {
            onAction(DriverCreateAction.DismissErrorDialog)
        }
    FleetWisorScaffold(
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
                        text = state.inputSurname,
                        placeholder = stringResource(R.string.surname) + "*",
                        hasLine = true
                    ) {
                        onAction(DriverCreateAction.InputSurname(it))
                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.fop,
                        text = state.inputName,
                        placeholder = stringResource(R.string.name) + "*",
                        hasLine = true
                    ) {
                        onAction(DriverCreateAction.InputName(it))

                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.phone,
                        text = state.inputPhone,
                        keyboardType = KeyboardType.Phone,
                        placeholder = stringResource(R.string.phone) + "*",
                        hasLine = true
                    ) {
                        onAction(DriverCreateAction.InputPhoneNumber(it))

                    }
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.calendar,
                        text = state.inputBirthDay,
                        placeholder = stringResource(R.string.birtday) + "*",
                        hasLine = true
                    ) {
                        onAction(DriverCreateAction.OpenDateSelector)
                    }

                    TitledLabelTextField(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        icon = FleetWisorTheme.icons.paid,
                        text = state.inputSalary,
                        unitText = stringResource(R.string.currency_uah_text),
                        placeholder = "100",
                        keyboardType = KeyboardType.Decimal,
                        titleText = stringResource(R.string.payment_for_month) + "*:",
                        onChange = {
                            onAction(DriverCreateAction.InputSalary(it))
                        }
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
                    modifier = Modifier.fillMaxWidth(0.8f),
                    icon = FleetWisorTheme.icons.numbers,
                    text = state.inputLicenseNumber,
                    alignByMin = true,
                    titleText = stringResource(R.string.driver_licence_number_text) + "*:",
                    placeholder = "0123",
                    onChange = {
                        onAction(DriverCreateAction.InputLicenseNumber(it))
                    }
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (state.frontPhoto == null)
                        SecondaryLongIconButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.front_page_document),
                            icon = FleetWisorTheme.icons.logout,
                            contentDescription = ""
                        ) {
                            pickFrontPhoto.launch("image/*")
                        }
                    else {
                        BorderImage(
                            modifier = Modifier.size(150.dp).clickable {
                                pickFrontPhoto.launch("image/*")
                            },
                            image = rememberAsyncImagePainter(state.frontPhoto),
                            contentDescription = ""
                        )
                    }
                    if (state.backPhoto == null)

                        SecondaryLongIconButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.back_page_document),
                            icon = FleetWisorTheme.icons.logout,
                            contentDescription = ""
                        ) {
                            pickBackPhoto.launch("image/*")
                        }
                    else {
                        BorderImage(
                            modifier = Modifier.size(150.dp).clickable {
                                pickBackPhoto.launch("image/*")
                            },
                            image = rememberAsyncImagePainter(state.backPhoto),
                            contentDescription = ""
                        )
                    }
                }
            }
            PrimaryButton(
                isLoading = state.savingInProgress,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = stringResource(R.string.save_text),
                enabled = state.canBeCreated,
                contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
            ) {
                onAction(DriverCreateAction.SaveDriver(context = context))
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