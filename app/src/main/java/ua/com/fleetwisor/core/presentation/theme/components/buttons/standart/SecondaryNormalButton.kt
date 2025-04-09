package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SecondaryNormalButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .height(FleetWisorTheme.dimensions.defaultButtonHeight)
            .border( width = 1.dp,color = FleetWisorTheme.colors.brandPrimaryNormal, shape = RoundedCornerShape(size = 10.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = true, color = FleetWisorTheme.colors.neutralPrimaryDark),
                onClick = onClick,
            )
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        ) {
        Text(
            text = text,
            style = FleetWisorTheme.typography.labelLargeM,
            color = FleetWisorTheme.colors.brandPrimaryNormal,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun SecondaryNormalButtonPreview() {
    SecondaryNormalButton(
        text = "Скасувати все",
    ) {

    }
}