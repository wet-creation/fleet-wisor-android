package ua.com.fleetwisor.core.presentation.ui.utils

import androidx.compose.runtime.Composable

data class TabItem(
    val title: String,
    val content: @Composable () -> Unit
)
