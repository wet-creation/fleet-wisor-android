package ua.com.agroswit.theme.components.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitChip(
    modifier: Modifier = Modifier,
    backgroundColor: Color = FleetWisorTheme.colors.brandPrimaryNormal,
    textColor: Color = FleetWisorTheme.colors.neutralPrimaryLight,
    textStyle: TextStyle = FleetWisorTheme.typography.labelSmallSB,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(size = 100.dp)
            ).then(modifier)
    ) {
        Text(
            modifier = Modifier.widthIn(max = 100.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = text,
            style = textStyle,
            color = textColor
        )
    }

}


@Preview
@Composable
private fun AgroswitChipPrev() {
    AgroswitChip(text = "Новинка")
}