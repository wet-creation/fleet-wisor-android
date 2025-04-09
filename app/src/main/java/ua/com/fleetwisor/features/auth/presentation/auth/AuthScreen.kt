package ua.com.fleetwisor.features.auth.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.agroswit.theme.components.buttons.standart.SecondaryNormalButton
import ua.com.agroswit.theme.components.scaffold.AgroswitBackground
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AuthScreenRoot(
    viewModel: AuthViewModel = koinViewModel()
) {
    AuthScreen(
        state = viewModel.state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun AuthScreen(
    state: AuthState,
    onAction: (AuthAction) -> Unit
) {
    AgroswitBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(
                100.dp,
                Alignment.CenterVertically
            )
        ) {
            Image(painter = painterResource(R.drawable.full_logo), contentDescription = "")
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login_text)
                ) { }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.weight(0.2f),
                        text = "aбо",
                        textAlign = TextAlign.Center,
                        style = FleetWisorTheme.typography.titleLarge,
                        color = FleetWisorTheme.colors.neutralSecondaryNormal
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))

                }
                SecondaryNormalButton(
                    modifier = Modifier.fillMaxWidth(),

                    text = stringResource(R.string.signup_text)
                ) {

                }
            }

        }
    }
}

@Preview
@Composable
private fun AuthStateScreenPreview() {
    FleetWisorTheme {
        AuthScreen(
            state = AuthState(),
            onAction = {}
        )
    }
}