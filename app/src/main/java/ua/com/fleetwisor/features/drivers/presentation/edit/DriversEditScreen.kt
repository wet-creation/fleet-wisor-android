package ua.com.fleetwisor.features.drivers.presentation.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.formatDate
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon.SecondaryOnlyIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryLongIconButton
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.fleetwisor.core.presentation.theme.components.dialogs.AgroswitDialog
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
fun DriversEditRoot(
    viewModel: DriversEditViewModel,
    navigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ObserverAsEvents(viewModel.events) {
        if (it is DriverEditEvents.DeletionComplete)
            navigateBack()
    }
    DriversEditScreen(
        state = state,
        onAction = {
            viewModel.onAction(it)

            when (it) {
                DriversEditAction.NavigateBack -> navigateBack()
                else -> {}
            }
        }
    )
}

@Composable
fun DriversEditScreen(
    state: DriversEditState,
    onAction: (DriversEditAction) -> Unit,
) {
    val context = LocalContext.current
    var showConfirmDeletion by remember {
        mutableStateOf(false)
    }
    var isDateSelectorOpen by remember {
        mutableStateOf(false)
    }

    val pickFrontPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onAction(DriversEditAction.SelectFrontPhoto(uri))
        }
    }
    val pickBackPhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            onAction(DriversEditAction.SelectBackPhoto(uri))
        }
    }
    if (state.error != emptyUiText)
        ConfirmationDialog(
            text = state.error.asString(),
            buttonText = stringResource(id = R.string.retry_text)
        ) {
            onAction(DriversEditAction.DismissErrorDialog)
        }
    if (isDateSelectorOpen) {
        DatePickerModal(
            selectedDate = state.editDriver.birthdayDate,
            onDismiss = {
                isDateSelectorOpen = false
            },
            onDateSelected = {
                isDateSelectorOpen = false
                onAction(DriversEditAction.SelectBirthDay(it ?: LocalDate.now()))
            }
        )
    }
    if (showConfirmDeletion)
        AgroswitDialog(
            onDismiss = { showConfirmDeletion = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 20.dp,
                        horizontal = 12.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.want_delete),
                    style = FleetWisorTheme.typography.headlineSmall,
                    color = FleetWisorTheme.colors.brandPrimaryNormal
                )
                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.want_delete_driver_text),
                    style = FleetWisorTheme.typography.bodyLarge,
                    color = FleetWisorTheme.colors.neutralSecondaryDark,
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    SecondaryNormalButton(
                        text = stringResource(R.string.cancel),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        showConfirmDeletion = false
                    }
                    PrimaryButton(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        text = "OK",
                    ) {
                        onAction(DriversEditAction.ConfirmDeleteDriver)
                        showConfirmDeletion = false

                    }
                }
            }
        }
    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.drivers_text)
            ) {
                onAction(DriversEditAction.NavigateBack)
            }
        },
        hasBottomBar = true,
    ) { paddingValues ->
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
        Column(
            modifier = Modifier
                .padding(
                    top = paddingValues.calculateTopPadding() + 20.dp,
                    bottom = paddingValues.calculateBottomPadding() + 20.dp
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
                        text = state.editDriver.surname,
                        placeholder = stringResource(R.string.surname) + "*",
                        hasLine = true
                    ) {
                        onAction(DriversEditAction.InputSurname(it))

                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.fop,
                        text = state.editDriver.name,
                        placeholder = stringResource(R.string.name) + "*",
                        hasLine = true
                    ) {
                        onAction(DriversEditAction.InputName(it))

                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.phone,
                        text = state.editDriver.phoneNumber,
                        keyboardType = KeyboardType.Phone,
                        placeholder = stringResource(R.string.phone) + "*",
                        hasLine = true
                    ) {
                        onAction(DriversEditAction.InputPhoneNumber(it))

                    }
                    LabelTextButton(
                        icon = FleetWisorTheme.icons.calendar,
                        text = state.editDriver.birthdayDate.formatDate(),
                        placeholder = stringResource(R.string.birtday) + "*",
                        hasLine = true
                    ) {
                        isDateSelectorOpen = true
                    }

                    TitledLabelTextField(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        icon = FleetWisorTheme.icons.paid,
                        text = state.inputSalary,
                        unitText = stringResource(R.string.currency_uah_text),
                        placeholder = "100",
                        titleText = stringResource(R.string.payment_for_month) + "*:",
                        onChange = {
                            onAction(DriversEditAction.InputSalary(it))
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
                    modifier = Modifier.fillMaxWidth(0.5f),
                    icon = FleetWisorTheme.icons.numbers,
                    text = state.editDriver.driverLicenseNumber,
                    titleText = stringResource(R.string.driver_licence_number_text) + "*",
                    placeholder = "0123",
                    onChange = {
                        onAction(DriversEditAction.InputLicenseNumber(it))
                    }
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    if (state.selectedFrontPhoto != null)
                        BorderImage(
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    pickFrontPhoto.launch("image/*")
                                },
                            image = rememberAsyncImagePainter(state.selectedFrontPhoto),
                            contentDescription = ""
                        )
                    else if (state.editDriver.frontLicensePhotoUrl.isNotEmptyOrBlank()) {
                        BorderImage(
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    pickFrontPhoto.launch("image/*")
                                },
                            image = rememberAsyncImagePainter(state.editDriver.frontLicensePhotoUrl),
                            contentDescription = ""
                        )
                    } else {
                        SecondaryLongIconButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.front_page_document),
                            icon = FleetWisorTheme.icons.logout,
                            contentDescription = ""
                        ) {
                            pickFrontPhoto.launch("image/*")
                        }
                    }
                    if (state.selectedBackPhoto != null)
                        BorderImage(
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    pickBackPhoto.launch("image/*")
                                },
                            image = rememberAsyncImagePainter(state.selectedBackPhoto),
                            contentDescription = ""
                        )
                    else if (state.editDriver.backLicensePhotoUrl.isNotEmptyOrBlank()) {
                        BorderImage(
                            modifier = Modifier
                                .size(150.dp)
                                .clickable {
                                    pickBackPhoto.launch("image/*")
                                },
                            image = rememberAsyncImagePainter(state.editDriver.backLicensePhotoUrl),
                            contentDescription = ""
                        )
                    } else {
                        SecondaryLongIconButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.back_page_document),
                            icon = FleetWisorTheme.icons.logout,
                            contentDescription = ""
                        ) {
                            pickBackPhoto.launch("image/*")
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PrimaryButton(
                    isLoading = state.isSaving,
                    text = stringResource(R.string.save_text),
                    enabled = (state.editDriver != state.driver) || state.selectedBackPhoto != null || state.selectedFrontPhoto != null,
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp),
                ) {
                    onAction(DriversEditAction.SaveDriver(context))
                }
                SecondaryOnlyIconButton(
                    tint = FleetWisorTheme.colors.errorDark,
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    icon = FleetWisorTheme.icons.delete,
                    contentDescription = "delete"
                ) {
                    showConfirmDeletion = true
                }
            }


        }
    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        DriversEditScreen(
            state = DriversEditState(),
            onAction = {}
        )
    }
}