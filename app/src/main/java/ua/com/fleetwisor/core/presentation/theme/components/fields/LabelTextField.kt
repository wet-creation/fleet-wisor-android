package ua.com.fleetwisor.core.presentation.theme.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme


@Composable
fun LabelTextButton(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    placeholder: String = "",
    hasLine: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            )
            .height(FleetWisorTheme.dimensions.spacing12),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.size(16.dp))
            if (text.isNotBlank()) Text(
                text = text,
                color = FleetWisorTheme.colors.neutralSecondaryDark,
                style = FleetWisorTheme.typography.labelLargeR
            ) else Text(
                text = placeholder,
                style = FleetWisorTheme.typography.labelLargeR,
                color = FleetWisorTheme.colors.neutralSecondaryNormal
            )
            Spacer(modifier = Modifier.size(8.dp))
            if (hasLine)
                HorizontalDivider(color = FleetWisorTheme.colors.neutralSecondaryLight)
        }
    }

}
@Composable
fun LabelTextField(
    modifier: Modifier = Modifier,
    icon: Painter,
    text: String,
    placeholder: String = "",
    hasLine: Boolean = false,
    onChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .height(FleetWisorTheme.dimensions.spacing12),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            tint = FleetWisorTheme.colors.brandPrimaryNormal,
            contentDescription = "",
            modifier = Modifier.size(20.dp)
        )
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.size(16.dp))

            SimpleTextFieldAgroswit(
                value = text,
                hint = placeholder,
                textStyle = FleetWisorTheme.typography.labelLargeR
            ) {
                onChange(it)
            }
            Spacer(modifier = Modifier.size(8.dp))
            if (hasLine)
                HorizontalDivider(color = FleetWisorTheme.colors.neutralSecondaryLight)
        }
    }

}

@Preview
@Composable
private fun LabelTextButtonPrev() {
    LabelTextButton(
        icon = FleetWisorTheme.icons.calculator,
        text = "Особистий кабінет",
        hasLine = true
    ) {

    }
}