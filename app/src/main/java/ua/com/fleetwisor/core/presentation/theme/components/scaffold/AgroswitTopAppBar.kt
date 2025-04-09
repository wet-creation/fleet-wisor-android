@file:OptIn(ExperimentalMaterial3Api::class)

package ua.com.fleetwisor.core.presentation.theme.components.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme
import ua.com.fleetwisor.core.presentation.ui.utils.ComposeWrapper

val topAppBarAgroswitColors
    @Composable
    get() =
        TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent,
            actionIconContentColor = FleetWisorTheme.colors.brandPrimaryNormal,
            navigationIconContentColor = FleetWisorTheme.colors.brandPrimaryNormal
        )

@Composable
fun AgroswitTopAppBar(
    modifier: Modifier = Modifier,
    startItem: (@Composable () -> Unit)? = null,
    endItems: List<ComposeWrapper> = listOf(),
    colors: TopAppBarColors = topAppBarAgroswitColors,
    content: (@Composable () -> Unit)
) {
    CenterAlignedTopAppBar(
        colors = colors,
        modifier = modifier,
        title = content,
        navigationIcon = {
            Box(modifier = Modifier.padding(start = 20.dp)) {
                startItem?.invoke()
            }
        },
        actions = {
            if (endItems.isNotEmpty()) {
                LazyRow(modifier = Modifier.padding(end = 20.dp)) {
                    items(endItems) { item ->
                        item.content()
                    }
                }
            }
        }
    )
}


@Composable
fun SimpleFilledAgroswitTopAppBar(
    title: String,
    onBackArrowPress: () -> Unit
) {
    AgroswitTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        startItem = {
            Icon(
                painter = FleetWisorTheme.icons.arrowBack, contentDescription = null,
                tint = FleetWisorTheme.colors.neutralPrimaryLight,
                modifier = Modifier.clickable(onClick = onBackArrowPress)
            )
        },
        colors = topAppBarAgroswitColors.copy(containerColor = FleetWisorTheme.colors.brandPrimaryNormal)
    ) {
        Row {
            Text(
                modifier = Modifier.fillMaxWidth(0.8f),
                overflow = TextOverflow.Ellipsis,
                text = title,
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = FleetWisorTheme.typography.headlineMedium,
                color = FleetWisorTheme.colors.neutralPrimaryLight
            )
        }
    }
}

@Preview
@Composable
private fun AgroswitTopAppBarPrev() {
    AgroswitTopAppBar(
        startItem = {
            Icon(
                painter = FleetWisorTheme.icons.hamburger,
                contentDescription = "",
                tint = FleetWisorTheme.colors.brandPrimaryNormal
            )
        },
        modifier = Modifier
            .fillMaxWidth(),


        endItems =
        listOf(ComposeWrapper {
            Icon(
                painter = FleetWisorTheme.icons.profile,
                contentDescription = ""
            )
        })
    ) {
        Row {
            Text(text = "BABA")
        }
    }
}