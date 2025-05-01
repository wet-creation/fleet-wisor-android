@file:OptIn(ExperimentalMaterial3Api::class)

package ua.com.agroswit.theme.components.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.theme.components.scaffold.AgroswitTopAppBar

@Composable
fun AuthBackground(
    onNavClick: () -> Unit,
    content: @Composable (ScrollState) -> Unit
) {
    val keyBoardPadding = WindowInsets.ime.asPaddingValues()

    val scrollState: ScrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = FleetWisorTheme.colors.neutralPrimaryNormal)
            .fillMaxSize()
            .navigationBarsPadding()
    ) {

        AgroswitTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            startItem = {
                Icon(
                    painter = FleetWisorTheme.icons.arrowBack,
                    contentDescription = null,
                    tint = FleetWisorTheme.colors.brandPrimaryNormal,
                    modifier = Modifier.clickable {
                        onNavClick()
                    }
                )
            },
            content = {},
            colors = TopAppBarDefaults.topAppBarColors().copy(
                containerColor = FleetWisorTheme.colors.brandPrimaryExtraLight
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            //DrawEllipseBackground(color = AgroswitTheme.colors.brandPrimaryExtraLight)
            Box(
                modifier = Modifier
                    .height(103.dp)
                    .background(color = FleetWisorTheme.colors.brandPrimaryExtraLight)
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = FleetWisorTheme.icons.agroswitLogoFull,
                    contentDescription = "Logo",
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = FleetWisorTheme.dimensions.spacing5)
                    .padding(bottom = keyBoardPadding.calculateBottomPadding())
            ) {
                content(scrollState)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}


@Preview
@Composable
private fun AuthBackgroundPreview() {
    FleetWisorTheme {
        AuthBackground(
            content = {},
            onNavClick = {}
        )
    }
}

