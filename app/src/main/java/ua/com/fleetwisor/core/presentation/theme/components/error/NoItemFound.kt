package ua.com.fleetwisor.core.presentation.theme.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.R

@Composable
fun NoItemFound(
    modifier: Modifier = Modifier,
    offset: Offset = Offset(0f, -100f),
    causeText: String = ""
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.offset(offset.x.dp, offset.y.dp)
        ) {
            Image(painter = FleetWisorTheme.icons.bees, contentDescription = "")
            Spacer(modifier = Modifier.height(FleetWisorTheme.dimensions.spacing9))
            Text(
                text = stringResource(id = R.string.not_found),
                style = FleetWisorTheme.typography.titleLarge,
                color = FleetWisorTheme.colors.neutralSecondaryDark
            )
            Spacer(modifier = Modifier.height(FleetWisorTheme.dimensions.spacing2))
            Text(
                textAlign = TextAlign.Center,
                text = if (causeText == "") stringResource(id = R.string.not_found_comment) else causeText,
                style = FleetWisorTheme.typography.labelLargeR,
                color = FleetWisorTheme.colors.neutralSecondaryDark
            )

        }

    }
}


@Preview
@Composable
private fun NoItemFoundPrev() {
    NoItemFound(modifier = Modifier.fillMaxSize())
}