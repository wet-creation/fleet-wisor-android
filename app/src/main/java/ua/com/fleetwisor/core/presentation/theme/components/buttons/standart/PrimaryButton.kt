package ua.com.agroswit.theme.components.buttons.standart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    contentPadding: PaddingValues? = null,
    isLoading: Boolean = false,
    onClick: () -> Unit
) {
    val isClick = remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    val buttonModifier =
        Modifier
            .height(FleetWisorTheme.dimensions.defaultButtonHeight)
            .then(
                if (enabled) modifier
                    .background(
                        color = if (!isClick.value) FleetWisorTheme.colors.brandPrimaryNormal else FleetWisorTheme.colors.brandPrimaryDark,
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
    Box(
        modifier = buttonModifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(15.dp)
                .alpha(if (isLoading) 1f else 0f),
            strokeWidth = 1.5.dp,
            color = FleetWisorTheme.colors.neutralPrimaryLight
        )
        Text(
            modifier = Modifier
                .then(
                    if (contentPadding != null) Modifier
                        .padding(contentPadding)
                    else Modifier
                )
                .then(
                    if (isLoading)
                        Modifier.alpha(0f)
                    else Modifier.alpha(1f)

                ),
            text = text,
            style = FleetWisorTheme.typography.labelLargeM,
            color = FleetWisorTheme.colors.neutralPrimaryLight,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun PrimaryLongButtonPreview() {
    PrimaryButton(
        isLoading = true,
        text = "Залишити заявку",
    ) {

    }
}