package ua.com.agroswit.theme.components.buttons.choice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.utils.noRippleClickable

@Composable
fun ChoiceButton(
    modifier: Modifier = Modifier,
    active: Boolean,
    text: String,
    onClick: () -> Unit
) {
    if (active) {
        Box(
            modifier = modifier
                .width(20.dp)
                .height(20.dp)
                .background(
                    color = FleetWisorTheme.colors.brandPrimaryNormal,
                    shape = RoundedCornerShape(size = 2.dp)
                )
                .clickable(onClick = onClick)

        ) {
            Text(
                text = text,
                color = FleetWisorTheme.colors.neutralPrimaryLight,
                style = FleetWisorTheme.typography.labelLargeR,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Box(
            modifier = modifier
                .width(20.dp)
                .height(20.dp)
                .border(
                    width = 1.dp,
                    color = FleetWisorTheme.colors.neutralSecondaryLight,
                    shape = RoundedCornerShape(size = 2.dp)
                )
                .noRippleClickable(onClick = onClick)
        ) {
            Text(
                text = text,
                color = FleetWisorTheme.colors.neutralSecondaryNormal,
                style = FleetWisorTheme.typography.labelLargeR,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
private fun ChoiceButtonPreview() {
    Column {
        ChoiceButton(active = true, text = "1") {

        }
        ChoiceButton(active = false, text = "1") {

        }

    }
}