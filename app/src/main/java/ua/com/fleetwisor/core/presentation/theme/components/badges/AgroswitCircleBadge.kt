package ua.com.fleetwisor.core.presentation.theme.components.badges

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitCircleBadge(
    modifier: Modifier = Modifier,
    icon: Painter,
    size: Dp = 28.dp,
    contentPadding: PaddingValues = PaddingValues(),
    tint: Color = FleetWisorTheme.colors.brandSecondaryNormal,
    background: Color = FleetWisorTheme.colors.brandPrimaryNormal
) {
    Box(
        modifier = modifier
            .size(size)
            .background(
                color = background,
                shape = CircleShape
            )
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = tint,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .align(
                    Alignment.Center
                )
        )
    }
}

@Preview
@Composable
private fun Prev() {
    AgroswitCircleBadge(icon = FleetWisorTheme.icons.promotionalOffersFilled)
}