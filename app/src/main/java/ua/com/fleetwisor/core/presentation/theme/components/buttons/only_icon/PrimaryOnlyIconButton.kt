package ua.com.fleetwisor.core.presentation.theme.components.buttons.only_icon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.agroswit.theme.components.utils.noRippleClickable

@Composable
fun PrimaryOnlyIconButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    contentDescription: String?,
    tint: Color = FleetWisorTheme.colors.brandSecondaryNormal,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                FleetWisorTheme.colors.brandPrimaryNormal,
                shape = RoundedCornerShape(size = 10.dp)
            )
            .height(38.dp)
            .fillMaxWidth()
            .padding(10.dp)
            .noRippleClickable (
                onClick = onClick
            )
            .then(modifier)


    ) {
        Icon(
            painter = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun PrimaryOnlyIconSmallButtonPreview() {
    PrimaryOnlyIconButton(
        icon = FleetWisorTheme.icons.filterUnSelected,
        contentDescription = ""
    ) {}

}