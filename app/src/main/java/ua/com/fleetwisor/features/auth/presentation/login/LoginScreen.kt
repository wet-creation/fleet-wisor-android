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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.scaffold.AgroswitBackground
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthPasswordTextField
import ua.com.fleetwisor.core.presentation.theme.components.fields.AuthTextField

@Composable
fun LoginScreenRoot(
    viewModel: LoginViewModel = koinViewModel()
) {
    LoginScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun LoginScreen(
    state: LoginState,
    onAction: (LoginAction) -> Unit
) {
    AgroswitBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(
                40.dp,
                Alignment.CenterVertically
            )
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
                        value = "",
                        onValueChange = {},
                        hint = "example@gmail.com",
                        title = stringResource(R.string.email_text)
                    )
                    AuthPasswordTextField(
                        onValueChange = {},
                        isPasswordVisible = false,
                        onTogglePasswordVisibility = {},
                        hint = stringResource(R.string.password_hint),
                        title = stringResource(R.string.password),
                    )
                }

                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login_text)
                ) { }

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