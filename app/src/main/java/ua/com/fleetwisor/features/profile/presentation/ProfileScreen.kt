package ua.com.fleetwisor.features.profile.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.dialogs.ConfirmationDialog
import ua.com.agroswit.theme.components.validation.PasswordRequirement
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.isNotEmptyOrBlank
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.fleetwisor.core.presentation.theme.components.fields.LabelTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.SimplePasswordTextFieldAgroswit
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.FleetWisorScaffold
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.SimpleFilledAgroswitTopAppBar
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.emptyUiText

@Composable
fun ProfileRoot(
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ProfileScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun ProfileScreen(
    state: ProfileState,
    onAction: (ProfileAction) -> Unit,
) {
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.saved) {
        if (state.saved && state.error == emptyUiText && state.passwordError == emptyUiText)
            snackBarHostState.showSnackbar(
                UiText.StringResource(R.string.user_info_update).asString(context),
                duration = SnackbarDuration.Long
            )
    }

    if (state.error != emptyUiText)
        ConfirmationDialog(
            text = state.error.asString(),
            buttonText = stringResource(id = R.string.retry_text)
        ) {
            onAction(ProfileAction.OnErrorCloseClick)
        }

    FleetWisorScaffold(
        topAppBar = {
            SimpleFilledAgroswitTopAppBar(
                title = stringResource(R.string.profile_text)
            )
        },
        hasBottomBar = true,
        snackbarHost = {
            SnackbarHost(snackBarHostState)
        }

    ) { paddingValue ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(
                    top = paddingValue.calculateTopPadding() + 20.dp,
                    bottom = paddingValue.calculateBottomPadding()
                )
                .padding(horizontal = 20.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.personal_data_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                Column(modifier = Modifier.fillMaxWidth(0.9f)) {
                    LabelTextField(
                        icon = FleetWisorTheme.icons.person,
                        text = state.newOwner.surname,
                        hasLine = true,
                        placeholder = stringResource(R.string.surname)
                    ) {
                        onAction(ProfileAction.InputSurname(it))
                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.fop,
                        text = state.newOwner.name,
                        hasLine = true,
                        placeholder = stringResource(R.string.name)

                    ) {
                        onAction(ProfileAction.InputName(it))

                    }
                    LabelTextField(
                        icon = FleetWisorTheme.icons.email,
                        text = state.newOwner.email,
                        hasLine = true,
                        placeholder = stringResource(R.string.email_example)
                    ) {
                        onAction(ProfileAction.InputEmail(it))
                    }

                }
                PrimaryButton(
                    isLoading = state.savingInProgress,
                    enabled = state.newOwner != state.owner,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.apply_text),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp)
                ) {
                    onAction(ProfileAction.ChangeInfo)
                }
                HorizontalDivider()
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    stringResource(R.string.change_password_text),
                    color = FleetWisorTheme.colors.brandPrimaryMedium,
                    style = FleetWisorTheme.typography.headlineMedium
                )
                Column {
                    SimplePasswordTextFieldAgroswit(
                        value = state.oldPassword,
                        isPasswordVisible = state.isPasswordVisible,
                        error = state.passwordError.asString(),
                        hint = stringResource(R.string.old_password),
                        onValueChange = {
                            onAction(ProfileAction.InputOldPassword(it))
                        }
                    ) {
                        onAction(ProfileAction.TogglePasswordVisibility)
                    }
                    SimplePasswordTextFieldAgroswit(
                        value = state.newPassword,
                        isPasswordVisible = state.isPasswordVisible,
                        hint = stringResource(R.string.new_password),
                        onValueChange = {
                            onAction(ProfileAction.InputNewPassword(it))
                        },
                        content = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                PasswordRequirement(
                                    text = stringResource(id = R.string.length_requirement),
                                    isValid = state.isPasswordValid.hasMinLength
                                )
                                PasswordRequirement(
                                    text = stringResource(id = R.string.characters_requirement),
                                    isValid = (state.isPasswordValid.hasUpperCaseCharacter && state.isPasswordValid.hasUpperCaseCharacter)
                                )
                                PasswordRequirement(
                                    text = stringResource(id = R.string.number_requirement),
                                    isValid = state.isPasswordValid.hasNumber
                                )
                            }
                        }
                    ) {
                        onAction(ProfileAction.TogglePasswordVisibility)

                    }
                    SimplePasswordTextFieldAgroswit(
                        value = state.confirmPassword,
                        isPasswordVisible = state.isPasswordVisible,
                        hint = stringResource(R.string.password_check_text),
                        onValueChange = {
                            onAction(ProfileAction.InputConfirmPassword(it))

                        },
                        error = if (state.newPassword.isNotEmpty() && state.confirmPassword.isNotEmpty() && state.newPassword != state.confirmPassword) {
                            stringResource(id = R.string.password_check_info)
                        } else {
                            null
                        },
                    ) {
                        onAction(ProfileAction.TogglePasswordVisibility)

                    }

                }
                PrimaryButton(
                    isLoading = state.savingInProgress,
                    enabled = state.oldPassword.isNotEmptyOrBlank() && state.isPasswordValid.isValidPassword && state.confirmPassword == state.newPassword,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(R.string.apply_text),
                    contentPadding = PaddingValues(vertical = 12.dp, horizontal = 32.dp)
                ) {
                    onAction(ProfileAction.SaveNewPassword)

                }
                HorizontalDivider()
            }

            SecondaryNormalButton(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 32.dp)
                    .align(Alignment.CenterHorizontally),
                text = stringResource(R.string.logout_text),
            ) {
                onAction(ProfileAction.OnLogOut)
            }

        }

    }
}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        ProfileScreen(
            state = ProfileState(),
            onAction = {}
        )
    }
}