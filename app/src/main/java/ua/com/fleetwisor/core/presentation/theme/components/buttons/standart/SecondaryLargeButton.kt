package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SecondLargeButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    tint: Color = FleetWisorTheme.colors.neutralPrimaryNormal,
    contentDescription: String?,
    text: String,
    onClick: () -> Unit,
) {
    val isClick = remember {
        mutableStateOf(false)
    }
    val coroutine = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .border(
                width = 1.dp,
                color = FleetWisorTheme.colors.neutralSecondaryLight,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(if (!isClick.value) Color.Transparent else FleetWisorTheme.colors.neutralSecondaryLight)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    onClick()
                    isClick.value = true
                    coroutine.launch {
                        isClick.value = false
                    }
                },
            )
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Icon(
            painter = painter,
            tint = tint,
            contentDescription = contentDescription,
            modifier = Modifier.size(64.dp)
        )

        Text(
            text = text,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun SecondLargeButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .size(150.dp)
            .border(
                width = 1.dp,
                color = FleetWisorTheme.colors.neutralSecondaryLight,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .background(Color.Transparent)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = FleetWisorTheme.colors.neutralPrimaryDark
                ),
                onClick = onClick,
            )
            .then(modifier),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.size(64.dp)
        )

        Text(
            text = text,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.neutralSecondaryDark,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun SecondLargeButtonPreview() {
    FleetWisorTheme {
        SecondLargeButton(
            painter = FleetWisorTheme.icons.catalogOutlined,
            text = "Засоби захисту рослин",
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
            contentDescription = "Продукти Іконка",
        ) {

        }

    }
}