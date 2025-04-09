package ua.com.agroswit.theme.components.scaffold

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

var bottomBar: @Composable () -> Unit = {}


@Composable
fun AgroswitScaffold(
    modifier: Modifier = Modifier,
    topAppBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    hasBottomBar: Boolean = false,
    floatingActionButtonPosition: FabPosition = FabPosition.Center,
    content: @Composable ColumnScope.(PaddingValues) -> Unit
) {
    Scaffold(
        topBar = topAppBar,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        bottomBar = {
            if (hasBottomBar)
                bottomBar()
        },
        modifier = modifier
    ) { padding ->
        AgroswitBackground {
            content(padding)
        }
    }

}