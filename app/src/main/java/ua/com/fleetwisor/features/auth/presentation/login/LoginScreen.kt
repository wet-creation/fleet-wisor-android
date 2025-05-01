package ua.com.fleetwisor.features.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.core.presentation.theme.components.dialogs.ConfirmationDialog
import ua.com.agroswit.theme.components.scaffold.AgroswitBackground
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthPasswordTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthTextField
import ua.com.fleetwisor.core.presentation.ui.utils.ObserverAsEvents
import ua.com.fleetwisor.features.auth.presentation.common.RegisterState

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel(),
    navigateMainMenu: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    ObserverAsEvents(viewModel.events) { event ->
        when (event) {
            is LoginEvent.Error -> {
                keyboardController?.hide()
            }

            LoginEvent.LoginSuccess -> {
                keyboardController?.hide()
                navigateMainMenu()
            }
        }
    }

    LoginScreen(
        state = viewModel.state,
        onAction = {
            viewModel.onAction(it)

        }
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    AgroswitBackground {
        if (state.loginState == RegisterState.ERROR) {
            ConfirmationDialog(
                text = state.error.asString(),
                buttonText = stringResource(id = R.string.retry_text)
            ) {
                onAction(LoginAction.OnErrorCloseClick)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(
                40.dp,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(R.drawable.full_logo), contentDescription = "")
            Column(
                verticalArrangement = Arrangement.spacedBy(56.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AuthTextField(
                        value = state.email,
                        onValueChange = {
                            onAction(LoginAction.InputEmail(it))
                        },
                        hint = "example@gmail.com",
                        title = stringResource(R.string.email_text)
                    )
                    AuthPasswordTextField(
                        value = state.password,
                        onValueChange = {
                            onAction(LoginAction.InputPassword(it))
                        },
                        isPasswordVisible = state.passwordVisible,
                        onTogglePasswordVisibility = {
                            onAction(LoginAction.TogglePasswordVisibility)
                        },
                        error = if (state.loginState == RegisterState.CONFLICT) state.error.asString() else null,
                        hint = stringResource(R.string.password_hint),
                        title = stringResource(R.string.password),
                    )
                }

                PrimaryButton(
                    isLoading = state.isLoggingIn,
                    enabled = state.canLogin,
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login_text)
                ) {
                    onAction(LoginAction.OnLogin)
                }

            }

        }

    }

}

@Preview
@Composable
private fun LoginScreenPreview() {
    FleetWisorTheme {
        LoginScreen(
            state = LoginState(),
            onAction = {}
        )
    }
}