package ua.com.agroswit.theme.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun AgroswitBackground(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = FleetWisorTheme.colors.neutralPrimaryNormal)
    ) {
        content()
    }
}