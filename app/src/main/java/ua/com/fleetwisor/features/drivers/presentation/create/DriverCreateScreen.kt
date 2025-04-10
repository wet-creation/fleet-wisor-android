package ua.com.fleetwisor.features.drivers.presentation.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun DriverCreateRoot(
    viewModel: DriverCreateViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DriverCreateScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun DriverCreateScreen(
    state: DriverCreateState,
    onAction: (DriverCreateAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        DriverCreateScreen(
            state = DriverCreateState(),
            onAction = {}
        )
    }
}