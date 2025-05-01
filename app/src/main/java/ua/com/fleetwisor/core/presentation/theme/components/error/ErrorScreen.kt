package ua.com.fleetwisor.core.presentation.theme.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton
import ua.com.fleetwisor.R

@Composable
fun ErrorScreen(errorMessage: String, modifier: Modifier = Modifier, buttonText: String? = null, onClick: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = FleetWisorTheme.icons.networkError, contentDescription = "")
        Spacer(modifier = Modifier.height(56.dp))
        Text(
            text = stringResource(id = R.string.connection_error_text),
            style = FleetWisorTheme.typography.headlineMedium,
            color = FleetWisorTheme.colors.brandPrimaryNormal
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = FleetWisorTheme.typography.bodyLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark
        )
        Spacer(modifier = Modifier.height(64.dp))
        PrimaryButton(text = if(buttonText.isNullOrBlank()) stringResource(id = R.string.retry_text) else buttonText, onClick = onClick, modifier = Modifier.fillMaxWidth(0.9f))
    }
}

@Preview
@Composable
private fun ErrorScreenPrev() {
    ErrorScreen("Перевірте підключення до Інтернету та повторіть спробу") {}

}