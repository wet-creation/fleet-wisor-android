package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun ThirdAgroswitButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.clickable(
            onClick = onClick,
            indication = null,
            interactionSource = remember {
                MutableInteractionSource()
            }
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = text, style = FleetWisorTheme.typography.labelLargeM, color = color)
    }
}

@Preview
@Composable
private fun ThirdAgroswitButtonPrev() {
    ThirdAgroswitButton(text = "Скасувати", color = FleetWisorTheme.colors.errorDark) {}
}