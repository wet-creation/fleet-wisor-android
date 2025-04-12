package ua.com.fleetwisor.features.cars.presentation.cars.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ua.com.fleetwisor.core.presentation.theme.FleetWisorTheme

@Composable
fun CarEditRoot(
    viewModel: CarEditViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CarEditScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
fun CarEditScreen(
    state: CarEditState,
    onAction: (CarEditAction) -> Unit,
) {

}

@Preview
@Composable
private fun Preview() {
    FleetWisorTheme {
        CarEditScreen(
            state = CarEditState(),
            onAction = {}
        )
    }
}