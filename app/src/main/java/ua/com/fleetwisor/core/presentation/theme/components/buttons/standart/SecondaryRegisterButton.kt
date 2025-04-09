package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun SecondaryRegisterButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val isClick = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val buttonModifier =
        Modifier
            .height(46.dp)
            .border(
                width = 1.5.dp,
                color = FleetWisorTheme.colors.brandPrimaryNormal,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .then(
                if (enabled) modifier
                    .background(
                        color = if (!isClick.value) Color.Transparent else FleetWisorTheme.colors.neutralPrimaryDark,
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
                    ) else modifier.background(
                    FleetWisorTheme.colors.brandPrimaryLight,
                    shape = RoundedCornerShape(size = 10.dp)
                )
            )

    Row(
        modifier = buttonModifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = text,
            style = FleetWisorTheme.typography.titleLarge,
            color = FleetWisorTheme.colors.brandPrimaryNormal,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PrimaryLongButtonPreview() {
    PrimaryButton(
        text = "Залишити заявку",
    ) {

    }
}