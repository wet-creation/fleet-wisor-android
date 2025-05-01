package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun PrimaryLargeButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    tint: Color = FleetWisorTheme.colors.neutralPrimaryNormal,
    contentDescription: String?,
    text: String,
    onClick: () -> Unit,
) {
    val size = DpSize(FleetWisorTheme.dimensions.largeButtonSize, FleetWisorTheme.dimensions.largeButtonSize)
    val isClick = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .size(size)
            .background(
                if (!isClick.value) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.brandPrimaryDark,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    onClick()
                    isClick.value = true
                    coroutineScope.launch {
                        delay(200)
                        isClick.value = false
                    }
                },
            )
            .padding(16.dp)
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Icon(
            painter = icon,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(size.width * 0.42f)
        )
        Text(
            text = text,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.neutralPrimaryLight,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PrimaryLargeButtonPreview() {
    FleetWisorTheme {
        PrimaryLargeButton(
            icon = FleetWisorTheme.icons.catalogFilled,
            text = "Продукти",
            tint = FleetWisorTheme.colors.brandSecondaryNormal,
            contentDescription = "Продукти Іконка",
        ) {

        }
    }
}