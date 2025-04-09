package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SecondaryLongIconButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: Painter,
    tint: Color = FleetWisorTheme.colors.brandPrimaryNormal,
    contentDescription: String?,

    onClick: () -> Unit,
) {
    val isClick = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(FleetWisorTheme.dimensions.secondButtonHeight)
            .border(
                width = 1.5.dp,
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(
                if (!isClick.value) FleetWisorTheme.colors.neutralPrimaryLight else Color.Transparent,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = FleetWisorTheme.colors.neutralPrimaryDark
                ),
                onClick = {
                    onClick()
                    isClick.value = true
                    coroutineScope.launch {
                        delay(200)
                        isClick.value = false
                    }
                },
            )
            .then(modifier),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = icon,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(22.dp)
        )
        Text(
            text = text,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.brandPrimaryNormal,
            textAlign = TextAlign.Start,
        )
    }
}

@Preview
@Composable
private fun SecondaryLongButtonPreview() {
    SecondaryLongIconButton(
        text = "Калькулятор цін",
        tint = FleetWisorTheme.colors.brandPrimaryNormal,
        icon = FleetWisorTheme.icons.calculator,
        contentDescription = ""
    ) {

    }
}