package ua.com.fleetwisor.core.presentation.theme.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.buttons.standart.PrimaryButton

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    head: String? = null,
    text: String,
    icon: Painter? = null,
    buttonText: String = "",
    secondaryText: String? = null,
    onClick: () -> Unit
) {
    AgroswitDialog(
        modifier = modifier,
        onDismiss = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 20.dp,
                    horizontal = 12.dp
                ),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            head?.let {
                Text(
                    text = head,
                    style = FleetWisorTheme.typography.headlineSmall,
                    color = FleetWisorTheme.colors.brandPrimaryNormal
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (icon != null) {
                    Icon(
                        painter = icon,
                        contentDescription = "",
                        tint = FleetWisorTheme.colors.brandPrimaryNormal,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Text(
                    style = FleetWisorTheme.typography.bodyLarge,
                    color = FleetWisorTheme.colors.neutralSecondaryDark,
                    text = text,
                    textAlign = if (icon == null) TextAlign.Center else TextAlign.Left
                )
            }
            secondaryText?.let {
                Text(
                    text = secondaryText,
                    style = FleetWisorTheme.typography.titleMedium,
                    color = FleetWisorTheme.colors.neutralSecondaryDark
                )
            }
            PrimaryButton(
                text = buttonText,
                contentPadding = PaddingValues(horizontal = 32.dp),
            ) {
                onClick()
            }
        }

    }
}

@Preview
@Composable
private fun ConfirmationDialogPreview(
) {
    FleetWisorTheme {
        ConfirmationDialog(
            head = null,
            icon = null,
            text = "",
            buttonText = "Повторити спробу",
            secondaryText = null
        ) {}
    }
}