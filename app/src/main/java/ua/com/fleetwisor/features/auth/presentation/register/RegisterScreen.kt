package ua.com.fleetwisor.features.auth.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.dialogs.ConfirmationDialog
import ua.com.agroswit.theme.components.scaffold.AgroswitBackground
import ua.com.agroswit.theme.components.validation.PasswordRequirement
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthPasswordTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthTextField
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState

@Composable
fun RegisterScreenRoot(
    viewModel: RegisterViewModel = koinViewModel(),
    navigateLogin: () -> Unit
) {
    RegisterScreen(
        state = viewModel.state,
        onAction = {
            viewModel.onAction(it)
            when (it) {
                RegisterAction.NavigateLogin -> navigateLogin()
                else -> {}
            }
        }
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterScreenState,
    onAction: (RegisterAction) -> Unit
) {
    AgroswitBackground {
        if (state.registerState == RegisterState.SUCCESS) {
            ConfirmationDialog(
                icon = FleetWisorTheme.icons.emailUnread,
                text = stringResource(id = R.string.email_confirm_send),
                buttonText = "OK"
            ) {
                onAction(RegisterAction.NavigateLogin)
            }
        }

        if (state.registerState == RegisterState.ERROR) {
            ConfirmationDialog(
                text = stringResource(id = R.string.register_error),
                buttonText = stringResource(id = R.string.retry_text)
            ) {
                onAction(RegisterAction.OnErrorRegistration)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(
                36.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.full_logo), contentDescription = "")
            Column(
                verticalArrangement = Arrangement.spacedBy(56.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AuthTextField(
                        value = state.name,
                        onValueChange = {
                            onAction(RegisterAction.OnNameChange(it))
                        },
                        hint = stringResource(R.string.your_name),
                        title = stringResource(R.string.name) + "*"
                    )
                    AuthTextField(
                        value = state.surname,
                        onValueChange = {
                            onAction(RegisterAction.OnSurnameChange(it))
                        },
                        hint = stringResource(R.string.your_surname),
                        title = stringResource(R.string.surname) + "*"
                    )
                    AuthTextField(
                        value = state.email,
                        onValueChange = {
                            onAction(RegisterAction.OnEmailChange(it))
                        },
                        isError = state.email.isNotEmpty() && !state.isEmailValid,
                        error = if (state.registerState == RegisterState.CONFLICT_EMAIL) {
                            state.error.asString()
                        } else null,
                        hint = "example@gmail.com",
                        title = stringResource(R.string.email_text) + "*"
                    )

                    AuthPasswordTextField(
                        onValueChange = {
                            onAction(RegisterAction.OnPasswordChange(it))

                        },
                        value = state.password,
                        isPasswordVisible = state.isPasswordVisible,
                        onTogglePasswordVisibility = {
                            onAction(RegisterAction.OnPasswordVisibilityClick)
                        },
                        hint = stringResource(R.string.password_hint),
                        title = stringResource(R.string.password) + "*",
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
                    )
                    AuthPasswordTextField(
                        onValueChange = {
                            onAction(RegisterAction.OnPasswordCheckChange(it))
                        },
                        value = state.passwordConformation,
                        isPasswordVisible = state.isPasswordVisible,
                        error = if (state.password.isNotEmpty() && state.passwordConformation.isNotEmpty() && state.password != state.passwordConformation) {
                            stringResource(id = R.string.password_check_info)
                        } else {
                            null
                        },
                        onTogglePasswordVisibility = {
                            onAction(RegisterAction.OnPasswordVisibilityClick)
                        },
                        hint = stringResource(R.string.password_check_hint),
                        title = stringResource(R.string.password_check_text) + "*"
                    )
                }

                PrimaryButton(
                    isLoading = state.isRegistering,
                    enabled = state.canRegister,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.signup_text)
                ) {
                    onAction(RegisterAction.OnRegisterClick)
                }

            }

        }

    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    FleetWisorTheme {
        RegisterScreen(
            state = RegisterScreenState(),
            onAction = {}
        )
    }
}